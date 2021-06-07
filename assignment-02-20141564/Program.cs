//Windows - VS code
using System;

namespace assignment_02_20141564
{
    class Program
    {
        static void Main(string[] args)
        {
            
            //creating canvas 
            Canvas myCanvas = new Canvas(500, 400);
            myCanvas.WhereTo = "myShapes.svg";

            //creating shapes, managing z-index
            //managing default line and fill styles for individual shape types 
            Rectangle r = new Rectangle(0,2,20,15);
            r.IndexLetter = 'b';
            r.StrokeWidth = 2544;
            r.StrokeColor = "grey";
            r.StrokeDash = 22;
            r.FillColor = "green";  

            Polyline linePoly = new Polyline("0,40 40,40 40,80 80,80 80,120 120,120 120,160");
            linePoly.IndexLetter = 'z';

            Polygon star = new Polygon("50 10 55 30 70 30 60 40 65 55 50 45 35 55 40 40 30 30 45 30");
            star.IndexLetter = 'p';
            star.FillColor = "darkblue";

            Path myPath = new Path("M 10 10 C 20 20, 40 20, 50 10");
            myPath.StrokeDash = 12;

            Line myLine = new Line(3, 7);
            myLine.IndexLetter = 'j';

            Ellipse myEllipse = new Ellipse(2, 15);      

            Circle c = new Circle(5, 3, 12);
            c.IndexLetter = 'a';

            Circle myCircle = new Circle(4);
            myCircle.IndexLetter = 'd';

            Rectangle r1 = new Rectangle(65,15);
            r1.IndexLetter= 'c';

            //canvas -- add, update, delete
            myCanvas.addShape(r.IndexLetter, r);
            myCanvas.addShape(linePoly.IndexLetter,linePoly);
            myCanvas.addShape(star.IndexLetter, star);
            myCanvas.addShape('e', myPath);
            myCanvas.addShape(myLine.IndexLetter, myLine);
            myCanvas.addShape('k', myEllipse);
            myCanvas.addShape(c.IndexLetter, c);
            myCanvas.addShape(myCircle.IndexLetter, myCircle);
            myCanvas.addShape(r1.IndexLetter, r1);

            myCanvas.updateShape(r1, r);

            myCanvas.removeShape(myCircle);
            myCanvas.removeShape('b');


            myCanvas.canvasToSVG();
        }
    }
}
