package com.tw;

import org.junit.Test;

import java.util.LinkedList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {

    @Test
    public void testMakeAChoice() throws Exception {
        Library library = new Library(3, "张三, 2, 数学:146.5, 英语:145.5, 编程:140, 语文:140");

        assertThat(library.MakeAChoice()).isTrue();
    }

    @Test
    public void testAddStudents() throws Exception {
        Library library = new Library(3, "张三, 2, 数学:146.5, 英语:145.5, 编程:140, 语文:140");
        StudentData studentData = new StudentData(2,"张三", 146.5, 140, 145.5, 140);

        assertThat(library.addnewstudent().Chinesegread).isEqualTo(140);
        assertThat(library.addnewstudent().englishgread).isEqualTo(145.5);
        assertThat(library.addnewstudent().programminggread).isEqualTo(140);
        assertThat(library.addnewstudent().mathgread).isEqualTo(146.5);
    }

    @Test
    public void testGetAList() throws Exception {
        Library library = new Library(3, "张三, 2, 数学:146.5, 英语:145.5, 编程:140, 语文:140");

        library.addnewstudent();
        library.inputchar = "1, 2";
        assertThat(library.GetAList()).isTrue();
    }

    @Test
    public void testMockClass() throws Exception {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        String value = "first";
        when(mockedList.get(0)).thenReturn(value);

        assertEquals(mockedList.get(0), value);

    }

}
