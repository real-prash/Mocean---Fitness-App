package com.example.moceanskeleton;

import com.example.moceanskeleton.application.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import android.content.Context;
import android.os.FileUtils;

import androidx.test.platform.app.InstrumentationRegistry;

import java.io.InputStream;
import java.nio.file.Files;

public class TestUtils {
    private static final String DB_SRC = "src/main/assets/db/MoceanDB.script";

    public static File copyDB() throws IOException {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        InputStream inputStream = context.getResources().getAssets().open(DB_SRC);
        File target = File.createTempFile("temp-db", ".script");
        FileOutputStream outputStream = new FileOutputStream(target);
        FileUtils.copy(inputStream, outputStream);
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}

