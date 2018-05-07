package cn.infisa;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.concurrent.Executors;

public class TestShell {

    @Test
    public void testShell() {
        String cmd="python3 /opt/danxi/daniDeal.py";
        System.out.println(exec(cmd));
//        executeCommand("ping -c 3 www.baidu.com");
    }
    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            int exitCode = p.waitFor();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
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
            String[] cmdA = { "/bin/sh", "-c", cmd };
            Process process = Runtime.getRuntime().exec(cmdA);
            int exitCode = process.waitFor();// 2 有异常，０　执行完成
            System.out.println(exitCode);
            System.out.println(process.getErrorStream());
            System.out.println(process.getOutputStream());
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
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
