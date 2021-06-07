using System;

namespace assignment_02_20141564
{
    class Rectangle : ClosedShape
    {        
        public double Width { get; set; }
        public double Height { get; set; }

        public static double strokeWidth = 6;
        private static double strokeDash = 6;
        private static string strokeColor = "purple";
        private static string fillColor = "yellow";

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
        public Rectangle(double width, double height) : base()
        {
            Width = width;
            Height = height;
        }

        public Rectangle(double x, double y, double width, double height) : base(x,y)
        {
            p.X = x;
            p.Y = y;
            Width = width;
            Height = height;
        }

        
        public override string shapeToSVG()
        {
           return 
           "<rect x=\"" + this.p.X + 
           "\" y=\"" + this.p.Y +
           "\" width=\"" + Width +
           "\" height=\"" + Height +
           "\"" + 
           base.shapeToSVG() +
           "/>\n";
        }

        public override string toString() => "Rectangle " + "(X=" + this.p.X + ",Y=" + this.p.Y + ",W=" + this.Width + ",H=" + this.Height + ")";
    }
}