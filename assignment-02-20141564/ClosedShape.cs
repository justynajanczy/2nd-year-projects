using System;

namespace assignment_02_20141564
{
    abstract class ClosedShape : Shape
    {
        private string fillColor = "transparent";
        public virtual string FillColor 
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
        public ClosedShape() : base() {}
        public ClosedShape(double x, double y) : base(x,y)
        {

        }

         public override string shapeToSVG()
        {
           return base.shapeToSVG() + (" fill=\"" + FillColor + "\"");
        }
    }
}