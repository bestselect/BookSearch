package com.bestselect.search;

public class SearchMeta{
    private long total_count;
    private boolean is_end;
    private long pageable_count;

    SearchMeta(){};

    public SearchMeta(long total_count, boolean is_end, long pageable_count) {
        this.total_count = total_count;
        this.is_end = is_end;
        this.pageable_count = pageable_count;
    }

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public boolean isIs_end() {
        return is_end;
    }

    public void setIs_end(boolean is_end) {
        this.is_end = is_end;
    }

    public long getPageable_count() {
        return pageable_count;
    }

    public void setPageable_count(long pageable_count) {
        this.pageable_count = pageable_count;
    }
}

