using System;

namespace assignment_02_20141564
{
    class Ellipse : ClosedShape
    {        
        //x, y radius of the circle separately
        //rx, ry radiuses of the ellipse
        //cx, cy position of the center of the ellipse
        public double RX { get; set; }
        public double RY { get; set; }

        private static double strokeWidth = 11;
        private static double strokeDash = 11;
        private static string strokeColor = "azure";
        private static string fillColor = "cadetblue";

        public override double StrokeWidth 
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
        public override double StrokeDash 
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
        public override string StrokeColor 
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
        public override string FillColor 
        { 
            get
            {
                return fillColor;
            }
            set
            {
                fillColor = value;
            } 
        }

        //constructors
        public Ellipse(double rx, double ry) : base()
        {
            RX = rx;
            RY=ry;
        }

        public Ellipse(double cx, double cy, double rx, double ry) : base(cx, cy)
        {
            RX = rx;
            RY = ry;
        }
        
        public override string shapeToSVG()
        {
           return 
           "<ellipse cx=\"" + this.p.X + 
           "\" cy=\"" + this.p.Y +
           "\" rx=\"" + RX +
           "\" ry=\"" + RY + "\"" +
           base.shapeToSVG() +
           "/>\n";
        }
    }
}