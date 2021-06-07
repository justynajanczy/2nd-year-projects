using System;
using System.IO;

namespace assignment_02_20141564
{
    class InterpretInput
    {
        private Canvas myCanvas;
        private CareTaker careTaker;
        char index = 'a';
        Boolean ifPrevUndo = false;

        //constructor
        public InterpretInput()
        {
            myCanvas = new Canvas(600, 600);
            careTaker = new CareTaker();
        }

        void checkCLI()
        {
            while(!string.IsNullOrEmpty(Console.ReadLine()))
            {
                string line = Console.ReadLine();
                string [] arr = line.Split(" ");

                if(arr[0] == "H")
                {
                    Console.WriteLine("Commands: \n" +
                    $"H         Help - displays this message\n" +
                    $"A <shape> Add <shape> to the canvas\n" +
                    $"U         Undo last operation\n" +
                    $"R         Redo last operation\n" +
                    $"C         Clear canvas\n" +
                    $"S         Save the canvas to a file\n" +
                    $"D         SVG code of canvas written to the console\n" +
                    $"Q         Quit application");
                }
                else if ( arr[0] == "A")
                {
                    string sh = arr[1].Substring(0,1).ToUpper() + arr[1].Substring(1).ToLower();
                    Random ra = new Random();
                    if(sh == "Circle")
                    {
                        myCanvas.addShape(index, new Circle(ra.NextDouble() * (300.000 - 0.1) + 0.1));
                        careTaker.save(myCanvas);
                        index++;  
                    }
                    else if(sh == "Ellipse")
                    {
                        myCanvas.addShape(index, new Ellipse(ra.NextDouble() * (600.000 - 0.1) + 0.1, ra.NextDouble() * (600.000 - 0.1) + 0.1));
                        careTaker.save(myCanvas);
                        index++; 
                    }
                    else if(sh == "Line")
                    {
                        myCanvas.addShape(index, new Line(ra.NextDouble() * (600.000 - 0.1) + 0.1, ra.NextDouble() * (600.000 - 0.1) + 0.1));
                        careTaker.save(myCanvas);
                        index++;
                    }
                    else if(sh == "Path")
                    {
                        //how to add multiple random numbers as a string
                        myCanvas.addShape(index, new Path((string)(ra.NextDouble() * (600.000 - 0.1) + 0.1 + " " + ra.NextDouble() * (600.000 - 0.1) + 0.1)));
                        careTaker.save(myCanvas);
                        index++;
                    }
                    else if(sh == "Polygon")
                    {
                        //same thing, add rand numbers as a string
                        myCanvas.addShape(index, new Polygon((string)(ra.NextDouble() * (600.000 - 0.1) + 0.1 + " " + ra.NextDouble() * (600.000 - 0.1) + 0.1)));
                        careTaker.save(myCanvas);
                        index++;
                    }
                    else if(sh =="Polyline")
                    {
                        myCanvas.addShape(index, new Polyline((string)(ra.NextDouble() * (600.000 - 0.1) + 0.1 + " " + ra.NextDouble() * (600.000 - 0.1) + 0.1)));
                        careTaker.save(myCanvas);
                        index++;
                    }
                    else if(sh=="Rectangle")
                    {
                        myCanvas.addShape(index, new Rectangle(ra.NextDouble() * (600.000 - 0.1) + 0.1, ra.NextDouble() * (600.000 - 0.1) + 0.1));
                        careTaker.save(myCanvas);
                        index++;
                    }
                    else 
                    {
                        Console.WriteLine("Wrong shape type provided.");
                    }
                }
                else if(arr[0] == "C")
                {
                    //clear
                    myCanvas.removeAll();
                }
                else if(arr[0] == "S")
                {
                    //save canvas to a file
                    myCanvas.canvasToSVG();
                }
                else if(arr[0] == "U")
                {
                    careTaker.revert(myCanvas);
                    ifPrevUndo = true;
                }
                else if(arr[0] == "R")
                {
                    if(ifPrevUndo)
                    {
                        careTaker.revert(myCanvas);
                    }
                }
                else if(arr[0] == "D")
                {
                    myCanvas.toString();
                }
                else if(arr[0] == "Q")
                {
                    break;
                }
            }
        }
    }
}
