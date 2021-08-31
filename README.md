

# 1.loading组件

## 1.1 library 使用方式
Activity里使用
```
    //oncreate
   noBoLoadingManager = new NoBoLoadingManager(this);

    //加载数据前
   noBoLoadingManager.showLoading();

   //加载失败
   noBoLoadingManager.showLoadFailed(new LoadingView.IRetryClickListener() {
                               @Override
                               public void retryClick() {
                                   initViews();
                               }
                           });

    //加载成功
     noBoLoadingManager.showLoadSuccess();

     //回收资源
     noBoLoadingManager.clear();

```

Fragment和view使用 类似

```
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, null);
        //传入根布局
        noBoLoadingManager = new NoBoLoadingManager(root);

        mImage = root.findViewById(R.id.image);

        initViews();

        //返回parent对象
        return (View) root.getParent();
    }



   noBoLoadingManager.showLoading();

   noBoLoadingManager.showLoadFailed(new LoadingView.IRetryClickListener() {
                             @Override
                             public void retryClick() {
                                 initViews();
                             }
                         });
    noBoLoadingManager.showLoadSuccess();


```
