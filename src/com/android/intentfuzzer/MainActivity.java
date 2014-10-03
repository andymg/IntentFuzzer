package com.android.intentfuzzer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.android.intentfuzzer.util.AppInfo;
import com.android.intentfuzzer.util.Utils;






import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	private GridView gridView = null;
	private final String ALL= "all";
	private final String SYSTEM = "system";
	private final String APPS ="apps";
	private List<AppInfo> listAppInfo = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		gridView=(GridView)findViewById(R.id.gridview);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setAdapter(new MainMenuAdapter(this));
        gridView.setOnItemClickListener(new OnItemClickListener(){ 
        	
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){  
          	 
          if(position==0){
          		
          		Intent intent=new Intent(MainActivity.this,AppInfoActivity.class);
          		intent.putExtra("type", Utils.ALL_APPS);
              	startActivity(intent);
          	}
          	
          	if(position==1){
         		
          		Intent intent=new Intent(MainActivity.this,AppInfoActivity.class);
          		intent.putExtra("type", Utils.SYSTEM_APPS);
              	startActivity(intent);
         	}
          	
          	if(position==2){
          		Intent intent=new Intent(MainActivity.this,AppInfoActivity.class);
          		intent.putExtra("type", Utils.NONSYSTEM_APPS);
              	startActivity(intent);
              		
         	}
            
          	if(position==3){        	
          		Dialog dialog = new Dialog(MainActivity.this, R.style.dialog);
          		dialog.setContentView(R.layout.dialog);
          		dialog.show();
          		
	
         	}
          	
          }  
        }); 
        //add by meng start
        
        Bundle extra = this.getIntent().getExtras();
        int action=0;
        String typeName ="";
        String res = "";
        if (extra != null) {
        	if(extra.containsKey("type")) {
        		String actionType = extra.getString("type");
        		Log.d("test","get action type"+actionType);
        		if ("all".equals(actionType)) {
        			action = Utils.ALL_APPS;
        			typeName = "ALL_APPS";
        		}
        		else if("apps".equals(actionType)) {
        			action = Utils.NONSYSTEM_APPS;
        			typeName = "NONSYSTEM_APPS";
        		}
        		else if("system".equals(actionType)){
        			action = Utils.SYSTEM_APPS;
        			typeName = "SYSTEM_APPS";
        		}
        		//res +=typeName+","+action;
				listAppInfo = Utils.getPackageInfo(this, Utils.ALL_APPS);
				for (int i = 0; i < listAppInfo.size(); i++) {
					AppInfo ai = listAppInfo.get(i);
					Log.d("test","packageName"+ai.getPackageName());
					res += typeName+","+ai.getPackageName()+","+ai.getAppName()+'\n';
				}
        	}
        	writeFile("application.cvs",res);
        	this.finish();
        }
        //add by meng end
	}
	public void writeFile(String fileName,String write_str) {
		File file = new File("/sdcard/"+fileName);
		try {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(write_str.getBytes());
		fos.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
