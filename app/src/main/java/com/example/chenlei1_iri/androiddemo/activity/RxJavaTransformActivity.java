package com.example.chenlei1_iri.androiddemo.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chenlei1_iri.androiddemo.R;
import com.example.chenlei1_iri.androiddemo.model.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaTransformActivity extends AppCompatActivity {
    private static final String TAG = RxJavaTransformActivity.class.getSimpleName();
    @BindView(R.id.btn_map)
    Button btnMap;
    @BindView(R.id.btn_flatmap)
    Button btnFlatmap;
    @BindView(R.id.btn_flatmap1)
    Button btnFlatmap1;
    @BindView(R.id.btn_filter)
    Button btnFilter;
    @BindView(R.id.btn_take)
    Button btnTake;
    @BindView(R.id.tv_rx_text)
    TextView tvRxText;
    @BindView(R.id.btn_interval)
    Button btnInterval;

    private StringBuilder sb;
    private Student[] student = new Student[3];
    ArrayList<Student.Course> course1 = new ArrayList<>();
    ArrayList<Student.Course> course2 = new ArrayList<>();
    ArrayList<Student.Course> course3 = new ArrayList<>();

    private int i = 0;
    private int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_transform);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sb = new StringBuilder();

        course1.add(new Student().new Course(0));
        course2.add(new Student().new Course(1));
        course3.add(new Student().new Course(2));
        student[0] = new Student(1, 23, "curry", "male", course1);
        student[1] = new Student(2, 25, "kobe", "male", course2);
        student[2] = new Student(3, 21, "taylor", "female", course3);
    }

    @OnClick({R.id.btn_map, R.id.btn_flatmap, R.id.btn_flatmap1, R.id.btn_filter, R.id.btn_take, R.id.btn_interval})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_map:
                testMap();
                break;
            case R.id.btn_flatmap:
                testFlatMap((i++) % 3);
                break;
            case R.id.btn_flatmap1:
                testFlatMap(j % 3);
                testFlatMap1(j % 3);
                j++;
                break;
            case R.id.btn_filter:
                int num = (int) (Math.random() * 100);
                Log.d(TAG, "num: " + num);
                testFilter(num);
                break;
            case R.id.btn_take:
                testTake();
                break;
            case R.id.btn_interval:
                testInterval();
                break;
        }
    }

    private void testMap() {
        Observable.just(new Date()).map(new Func1<Date, String>() {
            @Override
            public String call(Date date) {
                String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                return "Current time: " + s;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                sb.append(s).append("\n");
                tvRxText.setText(sb.toString());
            }
        });
    }

    private void testFlatMap(int i) {
        Subscriber<Student> subscriber = new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {
                int id = student.getId();
                int age = student.getAge();
                String name = student.getName();
                String sex = student.getSex();
                sb.append("id: " + id + " age: " + age + " name: " + name + " sex: " + sex + "\n");
                tvRxText.setText(sb.toString());
            }
        };

        Observable.just(student[i]).subscribe(subscriber);
    }

    private void testFlatMap1(int i) {
        Subscriber<Student.Course> subscriber = new Subscriber<Student.Course>() {
            @Override
            public void onCompleted() {
                sb.append("onCompleted\n");
                tvRxText.setText(sb.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student.Course course) {
                sb.append(course.getCourse() + "\n");
                tvRxText.setText(sb.toString());
            }
        };

        Observable.just(student[i]).flatMap(new Func1<Student, Observable<Student.Course>>() {
            @Override
            public Observable<Student.Course> call(Student student) {
                return Observable.from(student.getCourseList());
            }
        }).subscribe(subscriber);
    }

    private void testFilter(int i) {
        Observable.just(i).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                boolean isEvenNumber = integer % 2 == 0;
                return isEvenNumber;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                sb.append("Current number: " + integer + "\n");
                tvRxText.setText(sb.toString());
            }
        });
    }

    private void testInterval(){
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        sb.append("Complete ! \n");
                        tvRxText.setText(sb.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        if(aLong < 10){
                            sb.append(aLong + 1 + " s" + "\n");
                            tvRxText.setText(sb.toString());
                        }else {
                            onCompleted();
                        }
                    }
                });
    }

    private void testTake() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        sb.append("Complete ! \n");
                        tvRxText.setText(sb.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        sb.append(aLong + 1 + " s" + "\n");
                        tvRxText.setText(sb.toString());
                    }
                });
    }

}
