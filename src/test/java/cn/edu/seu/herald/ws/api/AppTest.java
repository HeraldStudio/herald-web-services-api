/*
 * The MIT License
 *
 * Copyright 2013 Herald Studio, Southeast University.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cn.edu.seu.herald.ws.api;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testMarshaller() throws JAXBException {
        Curriculum curriculum = new Curriculum();
        curriculum.setCardNumber("213100434");
        curriculum.setTerm("11-12-3");
        List<Course> courses = new LinkedList<Course>();
        Course c1 = new Course();
        c1.setCredit(0.5);
        c1.setLecturer("王老师");
        c1.setName("XX课程");
        c1.setWeek(new Period(1, 16));
        courses.add(c1);
        curriculum.setCourses(courses);
        TimeTable tt = new TimeTable();
        List<Schedule> schList = new LinkedList<Schedule>();
        Schedule s1 = new Schedule();
        Attendance at1 = new Attendance();
        at1.setCourseName("XX课程");
        at1.setPeriod(new Period(1, 3));
        at1.setPlace("J2");
        at1.setStrategy(StrategyType.ODD);
        s1.setAttendance(at1);
        s1.setDay(Day.FRI);
        tt.setSchedules(schList);
        curriculum.setTimeTable(tt);

        JAXBContext jaxbContext = JAXBContext.newInstance(Curriculum.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
        marshaller.marshal(curriculum, System.out);
    }

    public void testUnmarshaller() throws JAXBException {
        InputStream in = AppTest.class.getClassLoader()
                .getResourceAsStream("cn/edu/seu/herald/ws/api/curriculum.xml");
        if (in == null) {
            Assert.fail("The XML document is not found.");
            return;
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(Curriculum.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Curriculum curr = (Curriculum) jaxbUnmarshaller.unmarshal(in);

        // /curriculum/@term, /curriculum/@cardNumber
        Assert.assertEquals("12-13-3", curr.getTerm());
        Assert.assertEquals("213100434", curr.getCardNumber());

        // /curriculum/courses
        List<Course> courses = curr.getCourses();

        // count(/curriculum/courses/course)
        int lstLen = courses.size();
        Assert.assertEquals(4, lstLen);

        // /curriculum/courses/course[1]
        Course course1 = courses.get(0);
        // credit
        Assert.assertEquals(0.5, course1.getCredit());
        // lecturer
        Assert.assertEquals("", course1.getLecturer());
        // name
        Assert.assertEquals("IT新技术讲座", course1.getName());
        // week/@from
        Assert.assertEquals(1, course1.getWeek().getFrom());
        // week/@to
        Assert.assertEquals(16, course1.getWeek().getTo());

        // /curriculum/timeTable
        TimeTable timeTable = curr.getTimeTable();
        // /curriculum/timeTable/schedule
        List<Schedule> schedules = timeTable.getSchedules();
        // count(/curriculum/timeTable/schedule)
        int schLen = schedules.size();
        Assert.assertEquals(2, schLen);
    }
}
