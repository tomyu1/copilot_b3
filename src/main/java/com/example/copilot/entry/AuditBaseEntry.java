package com.example.copilot.entry;

public class AuditBaseEntry {

    private byte deleted;

    private long ctime;

    private String cuser;
    private long mtime;

    private String muser;

    public byte getDeleted() {
        return deleted;
    }

    public void setDeleted(byte deleted) {
        this.deleted = deleted;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getCuser() {
        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
    }

    public long getMtime() {
        return mtime;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }

    public String getMuser() {
        return muser;
    }

    public void setMuser(String muser) {
        this.muser = muser;
    }
}
