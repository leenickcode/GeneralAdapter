# GeneralAdapter
RecycleView适配器的封装，标准的适配器，和侧滑删除，拖拽排序的适配器。不用再频繁的去创建viewHoldr，也不用去写接口实现点击事件，只需继承对应的适配器，然后三部曲就可以了

#添加依赖
`compile 'com.lee:generaladapter:1.1.0'`

#基本使用

##普通效果

- 新建一个Adapter 继承 BaseRecyclerViewAdapter.
- 三部曲
```
        recyclerView=findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter=new TestAdapter(this,R.layout.item);
        recyclerView.setAdapter(adapter);
```
- 赋值
```
   @Override
    protected void bindData(UniversalViewHolder holder, int position, String bean) {
        holder.getTextView(R.id.textView).setText(bean);
    }
```
通过viewHolder和控件ID得到控件并赋值
- 设置点击事件
```
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position, View view, Object data) {
                
            }
        });
```
adapter 重写setItemChildListener方法
```
  @Override
    public void setItemChildListener(UniversalViewHolder helper, int viewType) {
      helper.setItemChildOnClickListener(R.id.textView);
    }
```
##侧滑删除and拖拽排序
- 新建一个Adapter 继承 SlideRecycleViewAdapter.
- 配置ItemTouchHelper
```
       ItemTouchHelperCallback callback = new ItemTouchHelperCallback();
        //一 继承SlideRecycleViewAdapter 设置监听器。  绑定RecycleView
        callback.setOnItemTouchCallbackListener(slideAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
```
- 必要的三部曲（不用再说了吧）
