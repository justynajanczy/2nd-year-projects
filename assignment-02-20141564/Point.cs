using System;

namespace assignment_02_20141564
{
    class Point
    {
        public double X {get; set;}
        public double Y { get; set;}

        public Point()
        {
            X = 0;
            Y = 0;
        }
        public Point(double x, double y)
        {
            X = x;
            Y = y;
        }

        public void moveTo(double newX, double newY)
        {
            X = newX;
            Y = newY;
        }
    }
}