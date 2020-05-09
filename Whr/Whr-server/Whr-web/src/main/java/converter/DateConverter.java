package converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DateConverter implements Converter<String, Data> {
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
    @Override
    public Data convert(String source) {
        try {
            return (Data) sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
