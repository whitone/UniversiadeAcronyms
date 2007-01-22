/*  
 *  Universiade Acronyms 0.1
 *
 *  Copyright (C) 2007 Stefano Cotta Ramusino
 *  All Rights Reserved.
 *
 *  The UniversiadeAcronyms midlet is free software; you can redistribute 
 *  it and/or modify it under the terms of the GNU General Public 
 *  License as published by the Free Software Foundation; either 
 *  version 2 of the License, or (at your option) any later version.
 *
 *  The UniversiadeAcronyms midlet is distributed in the hope that it will
 *  be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the UniversiadeAcronyms midlet; see the file COPYING.
 *  If not, write to the Free Software Foundation, Inc.,
 *  59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 */

import java.io.IOException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * The list of acronyms of Winter Universiade of Torino 2007.
 * <br><br>
 * Thanks to the customization of this class it's possible to
 * load other lists of acronyms and also translate the words
 * that are in the MIDlet to other languages.
 * <br><br>
 * Universiade Acronyms is released under the terms of GPL.
 * 
 * @version 0.1
 * @author Stefano Cotta Ramusino
 */
public class UniversiadeAcronyms extends MIDlet implements CommandListener {
	
	private static String MIDletname      = "Universiade Acronyms"   ;
	private static String MIDletversion   = "0.1"                    ;
	private static String MIDletauthor    = "Stefano Cotta Ramusino" ;
	private static String MIDletcopyright = "Copyright (C) 2007 " 
		                                    + MIDletauthor           ;
	
	/**
	 * The name of the Universiade.
	 * <br><br>
	 * <b>Example:</b> <code>Torino 2007</code>
	 */
	String universiade = "Torino 2007";
	
	/**
	 * True if the Universiade is in winter.
	 */
	boolean wintergame = true;
	
	/**
	 * The array of acronyms and their meanings.
	 * <br><br>
	 * The sintax of the elements is: <code>acronym: meaning</code>.<br>
	 * <b>Example:</b> <code>MPC: Main Press Centre</code>
	 */
	String[] acronyms = {
		
			"A: EC Member",
			"AG: EC Members Accompanying Person",
			"ALL: All Venues",
			"AS: Alpine Skying",
			"B: Commission Member",
			"BG: Commission Members Accompanying Person",
			"BDY: Bardonecchia",
			"BDY C: Bardonecchia Campo Smith",
			"BDY J: Bardonecchia Jafferau",
			"BDY M: Bardonecchia Melezet",
			"BH: Biathlon",
			"C: Chef Mission",
			"C1: Chef Mission Assistant",
			"CC: Cross Country",
			"CIC: International Control Committee",
			"CIO: International Olympic Committee",
			"CSS: Cesana San Sicario",
			"CU: Curling",
			"CUS: University Sports Centre",
			"CUSI: Italian University Sports Centre",
			"D: International Judge and Referee",
			"DT: Technical Staff",
			"E: Reporter",
			"EC: Executive Committee",
			"EN: Radio-TV Commentator",
			"ENG: Engineering News Gathering",
			"Ep: Photographer",
			"Et: Technicians",
			"EVS: Event Services",
			"f: Athlete",
			"F: Delegation Official and Medical Staff",
			"FFH: FISU Family Hotel",
			"FISU: International University Sports Federation",
			"FS: Figure Skating",
			"G: Guest",
			"GG: Accompanying Guest",
			"IBC: International Broadcasting Center",
			"ICE: All Indoor Venues",
			"IH: Ice Hockey",
			"IOC: International Olympic Committee",
			"ISF: Italian Sport Federation",
			"MAC: Main Accreditation Centre",
			"MDS: Master Delivery Schedule",
			"MPC: Main Press Center",
			"MVM: Marketing Venue Manager",
			"NC: Nordic Combined",
			"NOC: National Olympic Committee",
			"NUSF: National University Sports Federation",
			"O: Observer",
			"OBV: Outside Broadcast Van",
			"OC: Organizing Committee",
			"OCU: Organizing Committee of the Universiade",
			"OUT: All Outdoor Venues",
			"OVL: Oval Lingotto",
			"PAL: Palavela",
			"PIN: Pinerolo Palaghiaccio",
			"PRA: Pragelato",
			"PRP: Pragelato Plan",
			"PSO: Palasport Olimpico Isozaki",
			"RHB: TV Right Holders",
			"SB: Snowboarding",
			"SCP: Security Check Point",
			"SJ: Ski Jumping",
			"SS: Speed Skating",
			"ST: Short Track",
			"STL: Site Team Leader",
			"SY: Synchronized Skating",
			"T1: Assigned Car",
			"T2: Shared Assigned Car",
			"T3: FISU Family Motorpool on call",
			"T4: Media Motorpool on call",
			"TAZ: Tazzoli",
			"UVB: Universiade Village Bardonecchia",
			"UVP: Universiade Village Pragelato",
			"UVT: Universiade Village Torino",
			"VAPP: Vehicle Access & Parking Permits",
			"VIP: Very Important People",
			"WKF: Work Force"
			
	                     };
	
	/**
	 * The label of the About command.
	 */
	String aboutlabel    = "About"               ;
	
	/**
	 * The label of the Back command.
	 */
	String backlabel     = "Back"                ;
	
	/**
	 * The label of the Cancel command.
	 */
	String cancellabel   = "Cancel"              ;
	
	/**
	 * The text shown when search is completed.
	 */
	String completedtext = "Search completed!"   ;
	
	/**
	 * The label of the Exit command.
	 */
	String exitlabel     = "Exit"                ;
	
	/**
	 * The label of the Explain command.
	 */
	String explainlabel  = "Explain"             ;
	
	/**
	 * The label of the Find command.
	 */
	String findlabel     = "Find"                ;
	
	/**
	 * The title of the find section.
	 */
	String findtitle     = "Type an acronym"     ;
	
	/**
	 * The label of the Next command.
	 */
	String nextlabel     = "Next"                ;
	
	/**
	 * The label of the OK command.
	 */
	String oklabel       = "Search"              ;
	
	/**
	 * The label of the Search command.
	 */
	String searchlabel   = "Search text"         ;
	
	/**
	 * The title of the search section.
	 */
	String searchtitle   = "Type text to search" ;
	
	/**
	 * The label of the View command.
	 */
	String viewlabel     = "View"                ;
	
	/**
	 * The text shown when a searched string is not found.
	 */
	String[] notfoundtext = {
			
			"Text", 
			"not found!"
			
	                         };
	
	/**
	 * The text shown when an acronym is missing.
	 */
	String[] noacronymtext = {
			
			"Acronym", 
			"not found!"
			
	                         };
	
	/**
	 * The path of the MIDlet icon.
	 * <br><br> 
	 * <b>Example:</b> <code>"/icons/UniversiadeAcronyms.png"</code> or
	 *                 <code>"/icons/" + universiade.trim() + ".png</code> 
	 */
	String icon = "/icons/UniversiadeAcronyms.png";
	
	private List    list;
	private Display display;
	private Command nextCommand;
	private int     current    = 0     ;
	private boolean almostone  = false ;
	private String  findtext   = ""    ;
	private String  searchtext = ""    ;

	/**
	 * Create the list with 5 command: Find, Search text, View, About and Exit.
	 */
	public UniversiadeAcronyms() {
		
		Command exitCommand     = new Command(exitlabel,   Command.EXIT, 1);
		nextCommand             = new Command(nextlabel,   Command.ITEM, 2);
		Command findCommand     = new Command(findlabel,   Command.ITEM, 3);
		Command searchCommand   = new Command(searchlabel, Command.ITEM, 4);
		Command viewCommand     = new Command(viewlabel,   Command.ITEM, 5);
		Command aboutCommand    = new Command(aboutlabel,  Command.ITEM, 6);
		
		list = new List(universiade, List.IMPLICIT, acronyms, null);
		list.addCommand(exitCommand);
		list.addCommand(findCommand);
		list.addCommand(searchCommand);
		list.addCommand(viewCommand);
		list.addCommand(aboutCommand);
		list.setCommandListener(this);
		
		display = Display.getDisplay(this);
		
	}

	/**
	 * Display the list on device.
	 */
	protected void startApp() throws MIDletStateChangeException {
		
		display.setCurrent(list);

	}

	/**
	 * Called when the MIDlet is paused, for example, because of an incoming call.
	 */
	protected void pauseApp() {}

	/**
	 * Close correctly the MIDlet. 
	 */
	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		
		list        = null ;
		display     = null ;
		findtext    = null ;
		searchtext  = null ;
		nextCommand = null ;

	}

	/**
	 * Contains all the actions executed when an event occours.
	 */
	public void commandAction(Command cmd, Displayable d) {
		
		if (cmd.getLabel().equals(exitlabel)) {
			
			try {
				
				destroyApp(true);
				
			} catch (MIDletStateChangeException e) {}
			
			notifyDestroyed();
			
		}
		
		if (cmd.getLabel().equals(findlabel)) {
			
			Command explainCommand = new Command(explainlabel, Command.ITEM,     1);
			Command cancelCommand  = new Command(cancellabel,  Command.CANCEL, 2);
			
			TextBox find = new TextBox(findtitle, findtext, 10, TextField.ANY);
			find.addCommand(cancelCommand);
			find.addCommand(explainCommand);
			find.setCommandListener(this);
			
			display.setCurrent(find);
			
		}
		
		if (cmd.getLabel().equals(explainlabel)) {
			
			int curr      = 0     ;
			boolean found = false ;
			
			for (; curr < acronyms.length; curr++)
			{
				
				String acronym = acronyms[curr].toUpperCase();
				
				findtext = ((TextBox) d).getString();
				
				if (acronym.startsWith((findtext + ":").toUpperCase()))
				{
					found = true;
					break;
				}
				
			}
		
			if (found)
			{
			
				list.setSelectedIndex(curr, true);
				cmd = List.SELECT_COMMAND;
			
			} else {
			
				Alert noacronym = new Alert(universiade,
										    noacronymtext[0] 
										   + " \"" + findtext + "\" " 
										   + noacronymtext[1],
										   null,
										   AlertType.ERROR);
				noacronym.setTimeout(1000);

				display.setCurrent(noacronym, list);
					
			}
			
		}
		
		if (cmd == List.SELECT_COMMAND
				|| cmd.getLabel().equals(viewlabel)) {
			
			String selected = acronyms[list.getSelectedIndex()];
			
			Command backCommand = new Command(backlabel, Command.BACK, 1);
			
			String acronym = selected.substring(0, selected.indexOf(":"));
			String meaning = selected.substring(selected.indexOf(":") + 2);
			
			StringItem view = new StringItem("\n" + acronym, 
					                         "\n" + meaning);
			
			Form form = new Form(universiade);
			form.append(view);
			form.addCommand(backCommand);
			form.setCommandListener(this);
			
			display.setCurrent(form);
			
		}
		
		if (cmd.getLabel().equals(backlabel)) {
			
			display.setCurrent(list);
			
		}
		
        if (cmd.getLabel().equals(searchlabel)) { 
			
			Command okCommand     = new Command(oklabel,     Command.OK,     1);
			Command cancelCommand = new Command(cancellabel, Command.CANCEL, 2);
			
			TextBox search = new TextBox(searchtitle, searchtext, 60, TextField.ANY);
			search.addCommand(cancelCommand);
			search.addCommand(okCommand);
			search.setCommandListener(this);
			
			display.setCurrent(search);
			
		}
		
		if (cmd.getLabel().equals(oklabel)
				|| cmd.getLabel().equals(nextlabel)) {
			
			int original;
			original = current;
			
			if (cmd.getLabel().equals(oklabel))
			{
				
				if (!((TextBox) d).getString().equals(searchtext))
				{
					
					current = 0;
					almostone = false;
					list.removeCommand(nextCommand);
					
				} 
				else if (!((TextBox) d).getString().equals(""))
					
					current++;
				
				searchtext = ((TextBox) d).getString();
			}
			else
				current++;
			
			boolean found = false;
			
			if (searchtext.equals(""))
			{
				list.setSelectedIndex(current, true);
				list.removeCommand(nextCommand);
				display.setCurrent(list);
			}
			else
			{
				for (; current < acronyms.length; current++)
				{
					
					String acronym = acronyms[current].toUpperCase();
					
					if (acronym.indexOf(searchtext.toUpperCase()) != -1)
					{
						found = true;
						break;
					}
					
				}
			
				if (found)
				{
				
					if (current <= acronyms.length)
					{
						list.removeCommand(nextCommand);
						list.addCommand(nextCommand);
					}
					
					almostone = true;				
					list.setSelectedIndex(current, true);
					display.setCurrent(list);
				
				} else {
				
					list.removeCommand(nextCommand);
				
					if (original != current && almostone)
					{
						
						searchtext = "";
						almostone = false;
						Alert completed = new Alert(universiade,
								   				    completedtext,
								                    null,
								                    AlertType.INFO);
						completed.setTimeout(1000);

						display.setCurrent(completed, list);
						
						
					} else {
						
						current = 0;
						Alert notfound = new Alert(universiade,
												   notfoundtext[0] 
												   + " \"" + searchtext + "\" " 
												   + notfoundtext[1],
												   null,
												   AlertType.ERROR);
						notfound.setTimeout(1000);

						display.setCurrent(notfound, list);
						
					}
				}
			}
			
		}
		
		if (cmd.getLabel().equals(cancellabel)) {
			
			display.setCurrent(list);
			
		}
		
		if (cmd.getLabel().equals(aboutlabel)) {
			
			Image  logo   = null          ;
			String ogname = "Universiade" ;
			
			try {
				
				logo = Image.createImage(icon);
				
			} catch (IOException e) {}
			
			if (wintergame)
				ogname = "Winter " + ogname;
			
			String abouttext = MIDletname + " " + MIDletversion 
			                   + "\n\nThe list of acronyms"
	                           + " of " + ogname + " of " + universiade
	                           + "\n\nReleased under the terms of GPL\n"
	                           + MIDletcopyright; 
				
			Alert about = new Alert("Universiade Acronyms",
			                        abouttext,
			                        logo,
			                        null);
			about.setTimeout(Alert.FOREVER);
			
			display.setCurrent(about, list);
			
		}

	}

}
