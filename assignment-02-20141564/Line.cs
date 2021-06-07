using System;

namespace assignment_02_20141564
{
     class Line : Shape
    {
        Point p2 = new Point();

        private static double strokeWidth = 10;
        private static double strokeDash = 10;
        private static string strokeColor = "red";
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

        //constructors
        public Line(double x, double y) : base()
        {
            p2.X = x;
            p2.Y = y;
        }
        public Line(double x1, double y1, double x2, double y2) : base(x1,y1)
        {
            p2.X = x2;
            p2.Y = y2;
        }
        public override string shapeToSVG()
        {
           return 
           "<line x1=\"" + p.X + 
           "\" x2=\"" + p2.X +
           "\" y1=\"" + p.Y +
           "\" y2=\"" + p2.Y + "\"" + 
           base.shapeToSVG() +
           "/>\n";
        }
    }
}