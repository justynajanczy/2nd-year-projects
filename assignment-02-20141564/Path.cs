using System;
using System.Collections.Generic;

namespace assignment_02_20141564
{
    class Path : Shape
    {
        //the most general shape 
        //used to draw every other shapes and curves

        public string MyPath { get; set; }

        private static double strokeWidth = 9;
        private static double strokeDash = 9;
        private static string strokeColor = "orange";
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

        public Path (string argument)
        {
            MyPath = argument;
        }
        
        public override string shapeToSVG()
        {
           return 
           "<path d=\"" +
           MyPath +
           "\"" +
           base.shapeToSVG() +
           "/>\n";
        }
    }
}