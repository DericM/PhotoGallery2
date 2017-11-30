package com.example.deric.photogallery2;

/**
 * Created by Deric on 17/11/29.
 */

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.deric.photogallery2.Database.Image;
import com.example.deric.photogallery2.Database.MySQLiteHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SQLiteTest {

    private MySQLiteHelper mySQLiteHelper;

    private List<Image> testImages;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        mySQLiteHelper = new MySQLiteHelper(context);
        testImages = TestData.generateTestImages(context);
    }

    @After
    public void finish() {
        mySQLiteHelper.close();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(mySQLiteHelper);
    }

    @Test
    public void testShouldAddExpenseType() throws Exception {
        mySQLiteHelper.addImage(testImages.get(0));
        List<Image> images = mySQLiteHelper.getAllImages();

        assertThat(images.size(), is(1));
        //assertTrue(images.get(0).toString().equals("AUD"));
        //assertTrue(images.get(0).getValue().equals(1.2));
    }

    @Test
    public void testDeleteAll() {
        mySQLiteHelper.deleteAll();
        List<Image> rate = mySQLiteHelper.getAllImages();

        assertThat(rate.size(), is(0));
    }

    @Test
    public void testDeleteOnlyOne() {
        mySQLiteHelper.addImage(testImages.get(0));
        List<Image> rate = mySQLiteHelper.getAllImages();

        assertThat(rate.size(), is(1));

        mySQLiteHelper.deleteImage(rate.get(0));
        rate = mySQLiteHelper.getAllImages();

        assertThat(rate.size(), is(0));
    }

    @Test
    public void testAddAndDelete() {
        mySQLiteHelper.deleteAll();
        mySQLiteHelper.addImage(testImages.get(0));
        mySQLiteHelper.addImage(testImages.get(1));
        mySQLiteHelper.addImage(testImages.get(2));

        List<Image> images = mySQLiteHelper.getAllImages();
        assertThat(images.size(), is(3));

        mySQLiteHelper.deleteImage(images.get(0));
        mySQLiteHelper.deleteImage(images.get(1));

        images = mySQLiteHelper.getAllImages();
        assertThat(images.size(), is(1));
    }

}