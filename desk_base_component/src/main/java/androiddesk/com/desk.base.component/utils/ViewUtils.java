package androiddesk.com.desk.base.component.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androiddesk.base.component.R;
import com.androiddesk.base.component.utils.DimenUtils;
import com.androiddesk.base.component.widget.swiperecycler.SwipeRefreshRecyclerView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

public class ViewUtils {

    /**
     * 初始化RecyclerView：LinearLayoutManager 、itemDecoration
     * 默认颜色 app_line  默认高度0.5dp
     *
     * @param viewGroup       必须是RecyclerView或组合控件（XRecyclerView/EasyRecyclerView)
     * @param paddingLeftDip
     * @param paddingRightDip
     */
    public static void initRecyclerView(ViewGroup viewGroup, float paddingLeftDip, float paddingRightDip) {
        initRecyclerView(viewGroup, R.color.app_line, 0.5f, paddingLeftDip, paddingRightDip, true);
    }

    public static void initRecyclerView(ViewGroup viewGroup, int colorResId, float heightDip, float paddingLeftDip, float paddingRightDip) {
        initRecyclerView(viewGroup, colorResId, heightDip, paddingLeftDip, paddingRightDip, true);
    }

    public static void initRecyclerView(ViewGroup viewGroup, int colorResId, float heightDip, float paddingLeftDip, float paddingRightDip, boolean drawLastItem) {
        if (viewGroup == null) return;
        Context context = viewGroup.getContext();
        //layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        //DividerDecoration
        DividerDecoration itemDecoration = new DividerDecoration(context.getResources().getColor(colorResId),
                DimenUtils.dip2px(context, heightDip), DimenUtils.dip2px(context, paddingLeftDip),
                DimenUtils.dip2px(context, paddingRightDip));
        if (!drawLastItem) itemDecoration.setDrawLastItem(drawLastItem);

        if (viewGroup instanceof SwipeRefreshRecyclerView) {
            ((SwipeRefreshRecyclerView) viewGroup).setLayoutManager(layoutManager);
            ((SwipeRefreshRecyclerView) viewGroup).addItemDecoration(itemDecoration);
        } else if (viewGroup instanceof EasyRecyclerView) {
            ((EasyRecyclerView) viewGroup).setLayoutManager(layoutManager);
            ((EasyRecyclerView) viewGroup).addItemDecoration(itemDecoration);
        } else if (viewGroup instanceof RecyclerView) {
            ((RecyclerView) viewGroup).setLayoutManager(layoutManager);
            ((RecyclerView) viewGroup).addItemDecoration(itemDecoration);
        }
    }

    /**
     * 修复NestedScrollView 嵌套recyclerView(design库和V7库23.2以上）
     *
     * @param recyclerView
     */
    public static void fixRecyclerInsideScrollView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public static void setEmptyView(ViewGroup recyclerView, String msg) {
        View emptyView = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.customer_layout_empty, null);
        ((TextView) emptyView.findViewById(R.id.empty_tv)).setText(msg);
        if (recyclerView instanceof SwipeRefreshRecyclerView) {
            ((SwipeRefreshRecyclerView) recyclerView).setEmptyView(emptyView);
        } else if (recyclerView instanceof EasyRecyclerView) {
            ((EasyRecyclerView) recyclerView).setEmptyView(emptyView);
        }
    }

}
