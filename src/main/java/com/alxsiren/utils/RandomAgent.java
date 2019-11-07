package com.alxsiren.utils;

//Return random Agent for Windows or Linux

public class RandomAgent {
    public static String getAgent() {
        String agentToString = null;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            agentToString = getAgentForWindows();
        } else if(System.getProperty("os.name").toLowerCase().contains("linux")){
            agentToString = getAgentForLinux();
        }
        return agentToString;
    }

    private static String getAgentForWindows(){
        String agent = "";
        int random = (int )(Math.random() * 4);
        switch (random){
            case 0: agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36";
            break;
            case 1: agent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/70.0";
            break;
            case 2: agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36 OPR/64.0.3417.83";
            break;
            case 3: agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36 Vivaldi/2.8.1664.35";
        }
        return agent;
    }

    private static String getAgentForLinux(){
        String agent = "";
        int random = (int )(Math.random() * 4);
        switch (random){
            case 0: agent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36";
                break;
            case 1: agent = "Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/70.0";
                break;
            case 2: agent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36 OPR/64.0.3417.83";
                break;
            case 3: agent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36 Vivaldi/2.8.1664.35";
        }
        return agent;
    }
}
