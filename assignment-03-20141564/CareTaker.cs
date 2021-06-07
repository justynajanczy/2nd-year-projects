using System;
using System.Collections.Generic;

namespace assignment_02_20141564
{
    //part of the design pattern - holds state of the previous object 
    class CareTaker
    {
        Stack<Canvas.CanvasMemento> history = new Stack<Canvas.CanvasMemento>();

        //add to canvas 
        public void save(Canvas canvas)
        {
            history.Push(canvas.save());
        }

        public void revert(Canvas canvas)
        {
            if(history.Count !=0)
            {
                canvas.revert(history.Pop());
            }
            else
            {
                Console.WriteLine("Undo impossible");
            }
        }
    }
}