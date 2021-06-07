using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace assignment_02_20141564
{
    class Polygon : ClosedShape
    {
        public string MyPoly { get; set; }

        private static double strokeWidth = 8;
        private static double strokeDash = 8;
        private static string strokeColor = "green";
        private static string fillColor = "blue";

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

        public Polygon(string argument)
        {
            MyPoly = argument;
        }
        public override string shapeToSVG()
        {
           return 
           "<polygon points=\"" +
           MyPoly +
           "\"" +
           base.shapeToSVG() +
           "/>\n";
        }
    }

}