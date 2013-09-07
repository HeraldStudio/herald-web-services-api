package cn.edu.seu.herald.ws.api.impl.parser;

import cn.edu.seu.herald.ws.api.exercise.RunTime;
import cn.edu.seu.herald.ws.api.exercise.RunTimesData;
import cn.edu.seu.herald.ws.api.impl.UnmarshallerException;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ExerciseUnmarshaller extends AbstractUnmarshaller<RunTimesData> {

    @Override
    public RunTimesData unmarshall(InputStream xmlStream)
            throws UnmarshallerException {
        SAXReader reader = newSAXReader();
        try {
            Document document = reader.read(xmlStream);
            BigInteger times = BigInteger.valueOf(Long.parseLong(
                    selectSingleNodeText(document,
                            "/exer:runTimesData/@times")));
            BigDecimal rate = BigDecimal.valueOf(Double.parseDouble(
                    selectSingleNodeText(document,
                            "/exer:runTimesData/@rate")));
            List<String> timeNodes = selectTextNodes(document,
                    "/exer:times/exer:time");
            RunTimesData runTimesData = new RunTimesData();
            runTimesData.setRate(rate);
            runTimesData.setTimes(times);
            RunTime runTime = new RunTime();
            for (String timeNode : timeNodes) {
                XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
                        .newXMLGregorianCalendar(timeNode);
                runTime.getTimes().add(calendar);
            }
            runTimesData.setRunTime(runTime);
            return runTimesData;
        } catch (Exception ex) {
            throw new UnmarshallerException(ex);
        }
    }
}
