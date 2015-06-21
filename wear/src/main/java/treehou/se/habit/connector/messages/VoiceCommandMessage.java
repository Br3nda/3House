package treehou.se.habit.connector.messages;

public class VoiceCommandMessage {

    private String message;
    private long server;

    public VoiceCommandMessage(String message, long server) {
        this.message = message;
        this.server = server;
    }

    public VoiceCommandMessage(String message) {
        this(message, -1);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean haveServer(){
        return server >= 0;
    }

    public long getServer() {
        return server;
    }

    public void setServer(long server) {
        this.server = server;
    }
}
