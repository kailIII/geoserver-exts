package org.geoserver.importer.rest;

import static org.easymock.classextension.EasyMock.*;

import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import junit.framework.TestCase;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;

import org.geoserver.rest.PageInfo;
import org.geoserver.importer.ImportContext;
import org.geoserver.importer.ImportTask;
import org.geoserver.importer.Importer;
import org.geoserver.importer.rest.ImportJSONWriter;
import org.geoserver.importer.rest.ImportJSONReader;
import org.geoserver.importer.transform.ImportTransform;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author Ian Schneider <ischneider@opengeo.org>
 */
public abstract class TransformTestSupport extends TestCase {

    public void doJSONTest(ImportTransform transform) throws Exception {
        StringWriter buffer = new StringWriter();

        Importer im = createNiceMock(Importer.class);
        PageInfo pi = createNiceMock(PageInfo.class);
        
        replay(im, pi);

        ImportJSONWriter jsonio = new ImportJSONWriter(im, pi, buffer);

        ImportContext c = new ImportContext(0);
        c.addTask(new ImportTask());

        jsonio.transform(transform, 0, c.task(0), true, 1);

        ImportTransform transform2 = new ImportJSONReader(im, buffer.toString()).transform();
        PropertyDescriptor[] pd = BeanUtils.getPropertyDescriptors(transform.getClass());

        for (int i = 0; i < pd.length; i++) {
            assertEquals("expected same value of " + pd[i].getName(),
                    pd[i].getReadMethod().invoke(transform),
                    pd[i].getReadMethod().invoke(transform2));
        }
    }
}
