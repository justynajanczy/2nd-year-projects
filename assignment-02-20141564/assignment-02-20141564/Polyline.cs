using System;
using System.Collections.Generic;

namespace assignment_02_20141564
{
    class Polyline : Shape 
    {
        //polyline shape is a group of connected straight lines
        //and contains a single attribute 
        //end point of one line becomes the starting point for the next line
        public string MyPoly { get; set; }

private static double strokeWidth = 7;
        private static double strokeDash = 7;
        private static string strokeColor = "yellow";
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
        public Polyline(string argument)
        {
            MyPoly = argument;
        }

        public override string shapeToSVG()
        {
           return 
           "<polyline points=\"" +
           MyPoly +
           "\"" +
           base.shapeToSVG() +
           "/>\n";
        }
    }
}