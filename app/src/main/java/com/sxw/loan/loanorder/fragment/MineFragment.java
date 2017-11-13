package com.sxw.loan.loanorder.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.sxw.loan.loanorder.R;
import com.sxw.loan.loanorder.activity.LoginActivity;
import com.sxw.loan.loanorder.activity.MineMoneyActivity;
import com.sxw.loan.loanorder.activity.MineOrderActivty;
import com.sxw.loan.loanorder.activity.PushSettingActivity;
import com.sxw.loan.loanorder.activity.QuestionActivity;
import com.sxw.loan.loanorder.activity.RealNameActivity;
import com.sxw.loan.loanorder.activity.RealnameDetails;
import com.sxw.loan.loanorder.activity.SettingActivity;
import com.sxw.loan.loanorder.moudle.LoginRen;
import com.sxw.loan.loanorder.moudle.RealReg;
import com.sxw.loan.loanorder.moudle.RealnameBean;
import com.sxw.loan.loanorder.moudle.ShareBeab;
import com.sxw.loan.loanorder.moudle.ShareRet;
import com.sxw.loan.loanorder.moudle.User;
import com.sxw.loan.loanorder.ui.IPDialog;
import com.sxw.loan.loanorder.util.ConstantUrl;
import com.sy.alex_library.base.MyFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017-04-11.
 */

public class MineFragment extends MyFragment {
    @BindView(R.id.mine_head)
    SimpleDraweeView mineHead;
    @BindView(R.id.mine_myloan)
    RelativeLayout mineMyloan;
    //    @BindView(R.id.mine_question)
//    RelativeLayout mineQuestion;
    @BindView(R.id.mine_vip)
    RelativeLayout mineVip;
    @BindView(R.id.mine_customer)
    RelativeLayout mineCustomer;

    @BindView(R.id.mine_faster)
    RelativeLayout mineFaster;
    @BindView(R.id.name)
    TextView name;
    //    @BindView(R.id.phone)
//    TextView phone;
    @BindView(R.id.total_marks)
    TextView totalMarks;
    @BindView(R.id.markfalse)
    TextView markfalse;
    @BindView(R.id.vipscore)
    TextView vipscore;
    @BindView(R.id.information)
    LinearLayout information;
    @BindView(R.id.jifen)
    RelativeLayout jifen;
    @BindView(R.id.a25)
    ImageView a25;
    @BindView(R.id.txt89)
    TextView txt89;
    @BindView(R.id.a12)
    ImageView a12;
    @BindView(R.id.mine_share)
    RelativeLayout mineShare;
    @BindView(R.id.share)
    RelativeLayout share;
    @BindView(R.id.viewimage)
    View viewimage;
    @BindView(R.id.imageshimage)
    ImageView imageshimage;
    @BindView(R.id.lineimage)
    LinearLayout lineimage;
    //  private LinearLayout mine_logn;
    private String names, phones;
    private int userid;
    private String isinsert;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.main_nav_blue), 0);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        names = sharedPreferences.getString("name", "点击登录");
        userid = sharedPreferences.getInt("userid", 0);
        isinsert = sharedPreferences.getString("isinsert", "0");
        Log.e("useridhome", "userid" + isinsert);
//        if (userid > 0) {
//            name.setEnabled(false);
//            Uri uri = Uri.parse("res://com.xiaoweij.loan/" + R.mipmap.ic_launcher_round);
//            mineHead.setImageURI(uri);
//            information.setVisibility(View.VISIBLE);
//            Log.e("aaaaa___", sharedPreferences.getString("phone", "0") + "\n" + sharedPreferences.getString("pass", "0"));
//           logining(sharedPreferences.getString("phone", "0"), sharedPreferences.getString("pass", "0"));
//        } else {
//            Uri uri = Uri.parse("res://com.xiaoweij.loan/" + R.mipmap.person_img);
//            mineHead.setImageURI(uri);
//            name.setEnabled(true);
//        }
//        shareload(userid);
        return view;
    }

    private void loadreal(int userid) {
        RealReg phonecode = new RealReg();
        phonecode.setId(userid);
        OkHttpUtils
                .postString()
                .url(ConstantUrl.realnamesearch)
                .content(new Gson().toJson(phonecode))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("code", e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("code", response.toString());
                        RealnameBean realnameBean = new Gson().fromJson(response, RealnameBean.class);
                        if (realnameBean.getUserDetails() != null) {
                            Intent intent = new Intent(getContext(), RealnameDetails.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getContext(), RealNameActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    @OnClick({R.id.mine_myloan, R.id.mine_share, R.id.share, R.id.mine_vip, R.id.mine_customer, R.id.mine_faster, R.id.jifen})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share:
                if (userid != 0) {
                    if (isinsert.equals("0")) {
                        IPDialog.Builder builder = new IPDialog.Builder(getContext());
                        builder.setTitle("请去实名验证")
                                .setConfirmButton(new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                }).setCancelButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                loadreal(userid);
//                                Intent intent = new Intent(getContext(), RealNameActivity.class);
//                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }).create().show();
                    } else {

                        showShare(url);

                    }
                }
                break;
            case R.id.mine_share:
                Intent intent1 = new Intent(getContext(), QuestionActivity.class);
                startActivity(intent1);
                break;
            case R.id.mine_myloan:
                //我得J币
                if (userid != 0) {
                    if (isinsert.equals("0")) {
                        IPDialog.Builder builder = new IPDialog.Builder(getContext());
                        builder.setTitle("请去实名验证")
                                .setConfirmButton(new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                }).setCancelButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(getContext(), RealNameActivity.class);
//                                startActivity(intent);
//                                dialog.dismiss();
                                loadreal(userid);
                            }
                        }).create().show();
                    } else {
                        Intent intent = new Intent(getContext(), MineMoneyActivity.class);
                        startActivity(intent);

                    }
                } else {
                    IPDialog.Builder builder = new IPDialog.Builder(getContext());
                    builder.setTitle("请先登录")
                            .setConfirmButton(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            }).setCancelButton(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).create().show();

                }
                break;
//            case R.id.mine_question:
//                ToastUtils.showToastGravityCenter("功能暂未开放");
//
//                break;
            case R.id.mine_vip:
                //我的订单
                if (userid != 0) {
                    if (isinsert.equals("0")) {
                        IPDialog.Builder builder = new IPDialog.Builder(getContext());
                        builder.setTitle("请去实名验证")
                                .setConfirmButton(new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                }).setCancelButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                loadreal(userid);
//                                Intent intent = new Intent(getContext(), RealNameActivity.class);
//                                startActivity(intent);
//                                dialog.dismiss();
                            }
                        }).create().show();
                    } else {
                        Intent intent = new Intent(getContext(), MineOrderActivty.class);
                        startActivity(intent);

                    }
                } else {
                    IPDialog.Builder builder = new IPDialog.Builder(getContext());
                    builder.setTitle("请先登录")
                            .setConfirmButton(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            }).setCancelButton(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).create().show();

                }
                break;
            case R.id.mine_customer:
                //推送设置
                if (userid != 0) {
                    if (isinsert.equals("0")) {
                        IPDialog.Builder builder = new IPDialog.Builder(getContext());
                        builder.setTitle("请去实名验证")
                                .setConfirmButton(new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                }).setCancelButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(getContext(), RealNameActivity.class);
//                                startActivity(intent);
//                                dialog.dismiss();
                                loadreal(userid);
                            }
                        }).create().show();
                    } else {
                        Intent intent = new Intent(getContext(), PushSettingActivity.class);
                        startActivity(intent);

                    }
                } else {
                    IPDialog.Builder builder = new IPDialog.Builder(getContext());
                    builder.setTitle("请先登录")
                            .setConfirmButton(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            }).setCancelButton(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).create().show();

                }
                break;
            case R.id.mine_faster:
                //设置
                if (userid != 0) {
                    // ToastUtils.showToastGravityCenter("功能暂未开放");

                    Intent intent = new Intent(getContext(), SettingActivity.class);
                    startActivity(intent);
                } else {
                    IPDialog.Builder builder = new IPDialog.Builder(getContext());
                    builder.setTitle("请先登录")
                            .setConfirmButton(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            }).setCancelButton(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).create().show();

                }
                break;
            case R.id.jifen:
                //认证管理
                if (userid != 0) {
                    loadreal(userid);
                } else {
                    IPDialog.Builder builder = new IPDialog.Builder(getContext());
                    builder.setTitle("请先登录")
                            .setConfirmButton(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            }).setCancelButton(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).create().show();

                }
                break;
        }
    }

    private void logining(final String phoness, String pass) {
        OkHttpUtils
                .postString()
                .url(ConstantUrl.login)
                .content(new Gson().toJson(new User(phoness, pass)))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaaaa", e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("fragmentlogn", response.toString());
                        Gson gson1 = new Gson();
                        LoginRen regReturn = gson1.fromJson(response.toString(), LoginRen.class);
                        if (regReturn.getCode().equals("0")) {
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences("jidai",
                                    Activity.MODE_PRIVATE);
                            //实例化SharedPreferences.Editor对象
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            //用putString的方法保存数据
                            editor.putString("isinsert", String.valueOf(regReturn.getUser().getFlag()));
                            editor.putInt("userid", regReturn.getUser().getId());
                            editor.putString("name", regReturn.getUser().getName());
                            editor.putString("phone", regReturn.getUser().getPhone());
                            editor.putInt("total", regReturn.getUser().getIntegralSum());
                            editor.putInt("amount", regReturn.getUser().getAmount());
                            editor.apply();
                            if (regReturn.getUser().getFlag().equals("0")) {
                                viewimage.setVisibility(View.VISIBLE);
                                imageshimage.setVisibility(View.VISIBLE);
                                lineimage.setVisibility(View.VISIBLE);
                                imageshimage.setBackgroundResource(R.mipmap.icon_wsm);
                            } else {
                                viewimage.setVisibility(View.VISIBLE);
                                imageshimage.setVisibility(View.VISIBLE);
                                lineimage.setVisibility(View.VISIBLE);
                                imageshimage.setBackgroundResource(R.mipmap.icon_ysm);
                            }
                            if (regReturn.getUser().getVip().equals("0")) {
                                markfalse.setText("青铜");
                            } else if (regReturn.getUser().getVip().equals("1")) {
                                markfalse.setText("白银");
                            } else if (regReturn.getUser().getVip().equals("2")) {
                                markfalse.setText("黄金");
                            } else if (regReturn.getUser().getVip().equals("3")) {
                                markfalse.setText("白金");
                            } else if (regReturn.getUser().getVip().equals("4")) {
                                markfalse.setText("钻石");
                            } else if (regReturn.getUser().getVip().equals("5")) {
                                markfalse.setText("最强王者");
                            }
                            if (regReturn.getUser().getIntegralunused() >= 0) {
                                totalMarks.setText(regReturn.getUser().getIntegralunused() + "");
                            } else {
                                totalMarks.setText("0");
                            }
                        } else {
                        }
                    }
                });
    }

    private void shareload(int userid) {
        ShareBeab phonecode = new ShareBeab();
        phonecode.setUserId(userid);
        OkHttpUtils
                .postString()
                .url(ConstantUrl.shareurl)
                .content(new Gson().toJson(phonecode))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("code", e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("code", response.toString());
                        ShareRet shareRet = new Gson().fromJson(response, ShareRet.class);
                        url = shareRet.getUrl();
                    }
                });
    }

    private void showShare(String url) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("及速抢单");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("海量贷款订单 信贷员抢单神器");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://ac-lwepfm1e.clouddn.com/34c5c749a5e249ef4c55.png");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
// 启动分享GUI
        oks.show(getContext());

    }

    @Override
    public void onResume() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("jidai",
                Activity.MODE_PRIVATE);
        isinsert = sharedPreferences.getString("isinsert", "0");
        userid = sharedPreferences.getInt("userid", 0);
        shareload(userid);
        if (sharedPreferences.getInt("userid", 0) > 0) {
            name.setEnabled(false);
            information.setVisibility(View.VISIBLE);
            names = sharedPreferences.getString("name", "点击登录");
            name.setText(names);
            Uri uri = Uri.parse("res://com.xiaoweij.loan/" + R.mipmap.icon_logotwo);
            mineHead.setImageURI(uri);
            Log.e("aaaaa___", sharedPreferences.getString("phone", "0") + "\n" + sharedPreferences.getString("pass", "0"));
//            markfalse.setText(sharedPreferences.getInt("useedmark", 0));
//            totalMarks.setText(sharedPreferences.getInt("total",0));
            logining(sharedPreferences.getString("phone", "0"), sharedPreferences.getString("pass", "0"));
        } else {
            name.setEnabled(true);
            names = sharedPreferences.getString("name", "点击登录");
            name.setText(names);
            information.setVisibility(View.GONE);
            viewimage.setVisibility(View.GONE);
            imageshimage.setVisibility(View.GONE);
            lineimage.setVisibility(View.GONE);
            Uri uri = Uri.parse("res://com.xiaoweij.loan/" + R.mipmap.person_img);
            mineHead.setImageURI(uri);
        }
        super.onResume();
    }


}
