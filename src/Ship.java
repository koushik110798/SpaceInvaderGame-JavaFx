/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/

package com.vk.lab9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;


import java.awt.Rectangle;

public class Ship extends GameObject implements Object2D,Shooter<Laser> {

	public Ship(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public Laser fire() {
		// TODO Auto-generated method stub
		return new Laser(getX() + (getWidth()/2) - LASER_WIDTH/2,
				y - LASER_HEIGHT , LASER_WIDTH, LASER_HEIGHT);
	}

	@Override
	public String toString() {
		
		return "Ship at (" + getX() + ", "+ getY()+ ")";
		
	}

	
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(getX(), getY(), 40, 20);
		
		 // simple boat outline
        Polygon poly = new Polygon();
        poly.addPoint(x,y);
        poly.addPoint(x+30,y);
        poly.addPoint(x+25,y+12);
        poly.addPoint(x+10,y+12);
        poly.addPoint(x,y);

        // boat body
        g.setColor(Color.red);
        g.fillPolygon(poly);

        // mast
        g.setColor(new Color(225, 125, 105));
        g.fillRect(x+14,y-12,2,12);

        // sail
        g.setColor(Color.white);
        g.fillRect(x+16,y-12,6,8);
	}
	
}

