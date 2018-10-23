package com.example.lucifesar.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int maxMemory = (int) (Runtime.getRuntime().totalMemory()/1024);
        int cacheSize = maxMemory/8;
        LruCache<String,Bitmap> mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };

        LinkedHashMap mLinkedHashMap = new LinkedHashMap();
        
    }


    private String reverse(String str){

        String reverse = "";
        for(int i = 0;i<str.length();i++){
            reverse = str.charAt(i)+reverse;
        }
        return reverse;
    }

    //利用栈的先入后出的特性
    private String reverse1(String str){
        Stack<Character> s = new Stack<>();

        for(int i =0;i<str.length();i++){
            s.add(str.charAt(i));
        }
        String reverse = "";
        for(int i = 0;i<s.size();i++){
            reverse += s.pop();
        }
        return reverse;
    }

    public class ListNode{
        int data ;
        ListNode next;
    }

    public ListNode addListNode(ListNode l1,ListNode l2,int carry){

        if(l1==null&&l2==null&&carry==0) return null;

        ListNode result = new ListNode();
        int value = carry;

        if(l1!=null) value += l1.data;
        if(l2!=null) value += l2.data;

        result.data = value % 10;
        result.next = addListNode(l1==null?null:l1.next,null==l2?null:l2.next,value>10?1:0);
        return  result;
    }

    public void BubbleSort(int [] arr){

        int temp = 0;//临时变量
        boolean flag = false;//是否交换的标志

        for(int i =0;i<arr.length-1;i++){

            flag = false;
            for(int j = arr.length-1;j>i;j--){
                if(arr[j]<arr[j-1]){
                    temp = arr[j];
                    arr [j] = arr[j-1];
                    arr[j-1] = temp;
                    flag = true;
                }
            }
            if(!flag) break;
        }
    }

    public void SelectSort(int [] arr){

        int temp =0;

        for(int i = 0;i<arr.length-1;i++){

            int index = i;
            for(int j = i+1;j<arr.length;j++){

                if(arr[j]<arr[index]){
                    index = j;
                }
            }
            if(index!=i) {
                temp = arr[index];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
    }

    public void InsertSort(int arr[]){

        int temp ;

        for(int i =0;i<arr.length-1;i++){

            for(int j =i+1;j>0;j--){
                if(arr[j]<arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }else{
                    break;
                }
            }
        }
    }

    public void QuickSort(int [] arr ,int left,int right){
            if(left>=right) return;

            int pKey = arr[left];

            while (left<right){

                while(left<right&&arr[right]>=pKey)
                    right --;

                    arr[left] = arr[right];

                    while(left<right&&arr[left]<=pKey)
                        left ++;

                        arr[right] = arr[left];


            }

            arr[left] = pKey;

            QuickSort(arr,left,pKey-1);
            QuickSort(arr,pKey+1,right          );


    }




}
