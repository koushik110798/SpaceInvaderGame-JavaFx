/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/

package com.vk.lab9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Alien extends GameObject implements Shooter<Missile> {


	public Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
	}


	@Override
	public Missile fire() {
		return new Missile(getX() + (getWidth()/2) - MISSILE_WIDTH/2,
				y +  MISSILE_HEIGHT+MISSILE_WIDTH , MISSILE_WIDTH, MISSILE_HEIGHT);
	}
	
	@Override
	public String toString() {
		
		return "Alien at (" + getX() + ", "+ getY()+ ")";
		
	}


	public void draw(Graphics g) {
		int width = getWidth()-2;
		int height = getHeight()-2;
//		g.setColor(Color.RED);
//		g.fillRect(getX(), getY(), getWidth(), getHeight());
//		
		g.setColor(Color.YELLOW);
		int[] xpoints = { x, x+getWidth()/4, x+getWidth()/2, x };
		int[] ypoints = { y+3*getHeight()/4, y+3*getHeight()/4,  y+7*getHeight()/8, y+getHeight() };
		Polygon P = new Polygon(xpoints, ypoints, 4);
		g.fillPolygon(P);
		
		
		g.fillOval(getX() + width/4, getY()+height/4, width/3, height/3);
		
		g.setColor(Color.GRAY);
		g.fillOval(getX() + width/3, getY()+height/3, width/2, height/2);
		
		g.setColor(Color.GREEN);
		g.fillOval(getX() + 7*width/16, getY()+7*height/16, width/5, height/5);
		g.fillOval(getX() + 3*width/8, getY()+5*height/8, width/3, height/3);
		
		g.fillOval(getX() + 5*width/8, getY()+3*height/8, width/6, height/6);
		g.fillOval(getX() + 5*width/8, getY()+5*height/8, width/3, height/3);
		
		g.setColor(Color.WHITE);
		g.drawArc(getX() + 3*width/16+3, getY()+3*height/16+3, width/3, height/3, 0, 75);
		g.drawArc(getX() + 10*width/16+3, getY()+3*height/16+3, width/3, height/3, 75, 80);
		
//		g.setColor(Color.WHITE);
//		g.drawLine(getX(), getY(), getX()+getWidth(), getY()+getHeight());
//		g.drawLine(getX()+getWidth(), getY(), getX(), getY()+getHeight());
		
		
		
	}

}
