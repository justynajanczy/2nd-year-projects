using System;

namespace assignment_02_20141564
{
    abstract class Shape
    {
        public Point p = new Point();

        //line style, line thickness, line color - default values for all shapes 
        private double strokeWidth = 5;
        private double strokeDash = 5;
        private string strokeColor = "black";
        public virtual double StrokeWidth 
        { 
            get
            {
                return strokeWidth;
            }
            set
            {
                strokeWidth = value;
            } 
        }
        public virtual double StrokeDash 
        { 
            get
            {
                return strokeDash;
            }
            set
            {
                strokeDash = value;
            } 
        }
        public virtual string StrokeColor 
        { 
            get
            {
                return strokeColor;
            }
            set
            {
                strokeColor = value;
            } 
        }
        
        //to index objects
        private char indexLetter;
        public char IndexLetter{
            get
            {
                return indexLetter;
            }
            set
            {
                indexLetter = value;
            }
            } 

        //constructors
        public Shape()
        {

        }
        public Shape(double x, double y)
        {
            this.p.X = x;
            this.p.Y = y;
        }
    
        public virtual string shapeToSVG()
        {
            return 
            " stroke-width=\"" + StrokeWidth +  
            "\" stroke=\"" + StrokeColor + 
            "\" stroke-dasharray=\"" + StrokeDash +
            "\"";
        }

        public abstract string toString();
    }
}