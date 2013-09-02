package cn.edu.seu.herald.ws.api.impl.parser;

import cn.edu.seu.herald.ws.api.curriculum.*;
import cn.edu.seu.herald.ws.api.impl.UnmarshallerException;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class CurriculumUnmarshaller extends AbstractUnmarshaller<Curriculum> {

    private SAXReaderFactory saxReaderFactory = new SAXReaderFactory();

    @Override
    public Curriculum unmarshall(InputStream xmlStream)
            throws UnmarshallerException {
        SAXReader reader = saxReaderFactory.newSAXReader();
        try {
            Document document = reader.read(xmlStream);
            String term = selectSingleNodeText(document, "/curriculum/@term");
            String cardNumber = selectSingleNodeText(document,
                    "/curr:curriculum/@cardNumber");

            List<Node> courseNodes = selectNodes(document,
                    "/curr:curriculum/curr:courses/curr:course");
            Courses courses = new Courses();
            for (Node courseNode : courseNodes) {
                String href = selectSingleNodeText(courseNode, "@href");
                String name = selectSingleNodeText(courseNode,
                        "curr:name");
                String lecturer = selectSingleNodeText(courseNode,
                        "curr:lecturer");
                double credit = Double.parseDouble(selectSingleNodeText(
                        courseNode, "curr:credit"));
                int weekFrom = Integer.parseInt(selectSingleNodeText(courseNode,
                        "curr:week/@from"));
                int weekTo = Integer.parseInt(selectSingleNodeText(courseNode,
                        "curr:week/@to"));

                Period week = new Period();
                week.setFrom(BigInteger.valueOf(weekFrom));
                week.setTo(BigInteger.valueOf(weekTo));
                Course course = new Course();
                course.setCredit(BigDecimal.valueOf(credit));
                course.setHref(href);
                course.setLecturer(lecturer);
                course.setName(name);
                course.setWeek(week);
                courses.getCourses().add(course);
            }

            List<Node> timeTableNode = selectNodes(document,
                    "/curr:curriculum/curr:timeTable/curr:schedule");
            TimeTable timeTable = new TimeTable();
            for (Node scheduleNode : timeTableNode) {
                Day day = Day.valueOf(selectSingleNodeText(scheduleNode,
                        "@day"));
                String courseName = selectSingleNodeText(scheduleNode,
                        "curr:attendance/@courseName");
                String place = selectSingleNodeText(scheduleNode,
                        "curr:attendance/@place");
                Strategy strategy = Strategy.valueOf(selectSingleNodeText(
                        scheduleNode, "curr:attendance/@strategy"));
                int periodFrom = Integer.valueOf(selectSingleNodeText(
                        scheduleNode, "curr:attendance/curr:period/@from"));
                int periodTo = Integer.valueOf(selectSingleNodeText(
                        scheduleNode, "curr:attendance/curr:period/@to"));

                Period period = new Period();
                period.setFrom(BigInteger.valueOf(periodFrom));
                period.setTo(BigInteger.valueOf(periodTo));
                Schedule schedule = new Schedule();
                Attendance attendance = new Attendance();
                attendance.setCourseName(courseName);
                attendance.setPeriod(period);
                attendance.setPlace(place);
                attendance.setStrategy(strategy);
                schedule.getAttendances().add(attendance);
                schedule.setDay(day);
                timeTable.getSchedules().add(schedule);
            }

            Curriculum curriculum = new Curriculum();
            curriculum.setCardNumber(cardNumber);
            curriculum.setCourses(courses);
            curriculum.setTerm(term);
            curriculum.setTimeTable(timeTable);
            return curriculum;
        } catch (Exception ex) {
            throw new UnmarshallerException(ex);
        }
    }
}
