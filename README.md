

# 1.loading组件

## 1.1 library 使用方式

xml

```xml

    <LinearLayout

        android:id="@+id/failed"
        android:layout_width="match_parent"
        android:layout_height="222dp"
        android:layout_weight="1"
        android:orientation="vertical"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/success">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="测试"
            android:textSize="22sp" />

        <com.example.library.LoadingLayout
            android:id="@+id/loadinglayout2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <ImageView
                android:id="@+id/image2"
                android:layout_width="match_parent"
                android:layout_height="match_parent" />
        </com.example.library.LoadingLayout>
    </LinearLayout>

```
Activity里使用
```java
 
        mLoadinglayout = (LoadingLayout) findViewById(R.id.loadinglayout2);
        
        //失败重试
        mLoadinglayout.setRetryClickListener(() -> {

          Toast.makeText(LoadingActivity.this, "点击重试", Toast.LENGTH_SHORT).show();
            
        });

   
     
     mLoadinglayout.showLoading();
     mLoadinglayout.showLoadSuccess();
     
     mLoadinglayout.showLoadFailed(“可以自定义msg”);
     mLoadinglayout.showLoadEmpty("可以自定义msg");
   
            
```
 
