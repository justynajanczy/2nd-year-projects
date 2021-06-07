using System;

namespace assignment_02_20141564
{
    class Circle : Ellipse
    {
        private static double strokeWidth = 12;
        private static double strokeDash = 12;
        private static string strokeColor = "darkgreen";
        private static string fillColor = "firebrick";

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

        public Circle(double radius) : base(radius, radius)
        {

        }
        public Circle(double x, double y, double radius) : base (x, y, radius, radius)
        {

        }

        public override string shapeToSVG()
        {
           return 
           "<circle cx=\"" + p.X + 
           "\" cy=\"" + p.Y +
           "\" r=\"" + RX +
           "\" stroke-width=\"" + StrokeWidth +  
            "\" stroke=\"" + StrokeColor + 
            "\" stroke-dasharray=\"" + StrokeDash +
            "\""
            + 
            " fill=\"" + FillColor + "\"" +
            "/>\n";
        }

        public override string toString() => "Circle " + "(X=" + this.p.X + ",Y=" + this.p.Y + ",R=" + this.RX + ")";
    }
}