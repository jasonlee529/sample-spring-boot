package cn.infisa;

import org.junit.Test;

import java.io.*;
import java.util.concurrent.Executors;

public class TestShell {

    @Test
    public void testOne() throws IOException, InterruptedException {
        Process exec = Runtime.getRuntime().exec(new String[] { "uname" ,"-a"});
        exec.waitFor();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(exec.getInputStream()));
        System.out.println(exec.exitValue());
        System.out.println(reader.readLine());
    }
    @Test
    public void testShell() {
        String cmd = "python3 /opt/danxi/daniDeal.py";
        System.out.println(exec(cmd));
        executeCommand("ping -c 3 www.baidu.com");
    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            int exitCode = p.waitFor();
            System.out.println(exitCode);
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(output.toString());
        return output.toString();

    }

    public static Object exec(String cmd) {
        StringBuffer output = new StringBuffer();
        try {
            String[] cmdA = {"/bin/sh", "-c", cmd};
            Process process = Runtime.getRuntime().exec(cmdA);
            int exitCode = process.waitFor();// 2 有异常，０　执行完成
            InputStream is = null;
            if (exitCode == 0) {
                is = process.getInputStream();
            } else {
                is = process.getErrorStream();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                output.append(line + "\n");
            }
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
