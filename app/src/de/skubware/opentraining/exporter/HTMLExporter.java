/**
 * 
 * This is OpenTraining, an Android application for planning your your fitness training.
 * Copyright (C) 2012-2013 Christian Skubich
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */


package de.skubware.opentraining.exporter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import android.content.Context;
import android.webkit.WebView;


import de.skubware.opentraining.basic.FitnessExercise;
import de.skubware.opentraining.basic.Workout;
import de.skubware.opentraining.db.ContentProvider;
import de.skubware.opentraining.db.ContentProvider.Source;

public class HTMLExporter extends WorkoutExporter {
	private WebView webview;
	
	public HTMLExporter(Context context, WebView webview, Workout w) {
		super(context);
		this.webview = webview;

		this.loadWorkout(w);

	}
	
	private void loadWorkout(Workout w){
		File f = new File( context.getFilesDir().toString() + "/trainingplan.html" );
		ContentProvider.INSTANCE.writeFile(this.exportWorkoutToString(w), "trainingplan.html", context, context.getFilesDir());
		
		try {
			webview.loadUrl(f.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Exports a workout and returns the generated file.
	 * 
	 * @param w The workout to export
	 * 
	 * @return The File with the exported workout
	 * 
	 * @throws UnsupportedOperationException If not supported
	 */
	public File exportWorkoutToFile(Workout w){
		try{
			ContentProvider.INSTANCE.writeFile(this.exportWorkoutToString(w), "trainingplan.html", context, context.getFilesDir());
			
			return new File(context.getFilesDir().toString() + "/" + "trainingplan.html");
		}catch(UnsupportedOperationException unsupported){
			// may happen when String export doesn't work
			throw unsupported;
		}
		
	}
		

	
	@Override
	public String exportWorkoutToString(Workout w){
		//TODO FIX
		throw new UnsupportedOperationException();
		
		/*String data = "";

		try {
			//data = ContentProvider.INSTANCE.loadFileFromAssets("trainingplan_template.html",  context);
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}
		
		data = data.replaceAll("<!--WORKOUT_NAME-->", w.getName());
		
		// Exercises
		StringBuilder exes = new StringBuilder();
		for(FitnessExercise fEx:w.getFitnessExercises()){
				exes.append("\t\t <th>");
			exes.append(fEx.toString());
			exes.append("</th>\n");
		}
		data = data.replaceAll("<!--EXERCISES-->", exes.toString());
	
		
		//TODO: FSETS
		
		StringBuilder emptyRow = new StringBuilder();
		emptyRow.append("\t<tr>\n");
		// an empty row
		for(int i = 0; i<=w.getFitnessExercises().size(); i++){
				emptyRow.append("\t\t<td></td>\n");
		}
		emptyRow.append("\t</tr>\n");

		StringBuilder emptyRowOdd = new StringBuilder();
		emptyRowOdd.append("\t<tr class=\"alt\">\n");
		// an empty row
		for(int i = 0; i<=w.getFitnessExercises().size(); i++){
			emptyRowOdd.append("\t\t<td></td>\n");
		}
		emptyRowOdd.append("\t</tr>\n");
		
		
		StringBuilder emptyCells = new StringBuilder();
		boolean even = false;
		for(int i = 0; i< w.getEmptyRows(); i++){
			if(even)
				emptyCells.append(emptyRowOdd.toString());
			else
				emptyCells.append(emptyRow.toString());
			even=!even;
		}
		
		data = data.replaceAll("<!--EMPTY_CELLS-->", emptyCells.toString() );
		
		

		data = data.replaceAll("<!--CSS-->", ContentProvider.INSTANCE.getCSSFileAsString(context));
		
		
		
		return data;*/	
		
	}

}