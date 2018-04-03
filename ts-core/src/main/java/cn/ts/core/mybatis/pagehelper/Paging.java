package cn.ts.core.mybatis.pagehelper;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页器，根据 page, size, total 用于页面上分页显示多项内容，计算页码和当前页的偏移量，方便页面分页使用.
 * <pre>
 *     page: 页数。默认：1
 *     size：分页大小。默认：25
 *     total：总记录数。如果不查询总记录数，则返回0，否则返回具体总记录数
 * </pre>
 *
 * @author Create by YL on 2017/05/29.
 */
public class Paging<E> implements Serializable {
    private static final long serialVersionUID = -2429864663690465105L;
    private long page = 1; // 当前页
    private long size = 10; // 每页显示记录数
    private long total; // 总记录数
    private long pages; // 总页数

    private long start = 1; // 每页起始数（从1开始）
    private long end; // 每页结束数
    private long prev = 1; // 上一页（从1开始）
    private long next; // 下一页
    private boolean hasPrev; // 判断是否有上一页
    private boolean hasNext; // 判断是否有下一页

    private List<E> list = new ArrayList<>(); // 查询结果集

    public Paging(long page, long size) {
        this.page = page;
        this.size = size;
    }

    // Page{count=false, pageNum=1, pageSize=2, startRow=0, endRow=2, total=-1, pages=1, reasonable=false,
    // pageSizeZero=false}
    public Paging(Page<E> p) {
        this(p.getPageNum(), p.getPageSize());
        // if (p == null) {
        //     throw new NullPointerException("Page<E> Object Cannot Be NULL");
        // }
        // this.page = p.getPageNum();
        // this.size = p.getPageSize();
        this.total = p.getTotal();
        this.pages = p.getPages();
        this.start = p.getStartRow();
        this.end = p.getEndRow();
        for (E e : p) {
            this.list.add(e);
        }
    }

    public Paging() {

    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getPage() {
        if (page > this.getPages())
            page = this.getPages();
        if (page <= 0)
            page = 1;
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        long total = this.getTotal();
        long size = this.getSize();
        if ((total % size) == 0) {
            pages = total / size;
        } else {
            pages = total / size + 1;
        }
        return pages == 0 ? 1 : pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    /**
     * 开始行，可用于Oracle、MySQL等分页
     * <pre>
     * Oracle: rownum <= endRow and rownum > startRow
     * MySQL: limit startRow, pageSize
     * </pre>
     */
    public long getStart() {
        start = this.getPage() > 0 ? (this.getPage() - 1) * this.getSize() : 0;
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    /**
     * 结束行，可以用于oracle分页使用
     * Oracle: rownum <= endRow
     */
    public long getEnd() {
        end = this.getStart() + this.getSize();
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getPrev() {
        if (isHasPrev()) {
            this.prev = getPage() - 1;
        } else {
            this.prev = getPage();
        }
        return this.prev;
    }

    public void setPrev(long prev) {
        this.prev = prev;
    }

    public long getNext() {
        if (isHasNext()) {
            this.next = getPage() + 1;
        } else {
            this.next = getPage();
        }
        return this.next;
    }

    public void setNext(long next) {
        this.next = next;
    }

    public boolean isHasPrev() {
        this.hasPrev = this.getPage() - 1 >= 1;
        return this.hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public boolean isHasNext() {
        this.hasNext = getPage() + 1 <= getPages();
        return this.hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(this.getClass().getSimpleName());
        sb.append(" [");
        sb.append("page=").append(this.getPage());
        sb.append(", size=").append(this.getSize());
        sb.append(", total=").append(this.getTotal());
        sb.append(", pages=").append(this.getPages());
        sb.append(", start=").append(this.getStart());
        sb.append(", end=").append(this.getEnd());
        sb.append(", prev=").append(this.getPrev());
        sb.append(", next=").append(this.getNext());
        sb.append(", hasPrev=").append(this.isHasPrev());
        sb.append(", hasNext=").append(this.isHasNext());
        sb.append(", list=").append(list);
        sb.append("]");
        return sb.toString();
    }
}
