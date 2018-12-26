using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Drawing;
using System.Diagnostics;


namespace TRSTPO
{


    class Program
    {
        
        static void Main(string[] args)
        {
            CentralObject central = new CentralObject();
            ChildObject[] children = new ChildObject[20];
            for (int i = 0; i < 20; i++)
            {
                children[i] = new ChildObject(central);
            }
            central.Link(children);
            //------ Для централизованной обработки ---------
            //for (; ; )
            //{
            //    central.CentralDataProcessing();
            //    Thread[] threads = new Thread[20];
            //    for (int i = 0; i < 20; i++)
            //    {
            //        threads[i] = new Thread(children[i].ChildSend);
            //        threads[i].Start();
            //    }
            //    Console.WriteLine($"Кол-во блоков на локальных компьюетрах: {children[19].Blocks}");
            //    for (int i = 0; i < 20; i++)
            //    {
            //        threads[i].Join();
            //    }
            //    if (central.Blocks == 0)
            //        break;
            //    Thread.Sleep(100);
            //}
            //Console.ReadKey();
            //-----------------------------------------------

            //------------Для предварительной обработки------
            //for (; ; )
            //{
            //    central.CentralDataProcessing();
            //    if (children[19].Blocks != 20)
            //    {
            //        if (children[19].Blocks > 16)
            //        {
            //            central.CentralSend();
            //            Console.WriteLine($"Кол-во блоков на локальных компьюетрах после отправки с главного: {children[19].Blocks}");
            //        }

            //    }

            //    Thread[] threads = new Thread[40];
            //    for (int i = 0; i < 20; i++)
            //    {
            //        threads[i] = new Thread(children[i].ChildDataProcessing);
            //        threads[i].Start();
            //    }
            //    //Console.WriteLine($"Кол-во блоков на локальных компьюетрах: {children[19].Blocks}");
            //    for (int i = 0; i < 20; i++)
            //    {
            //        threads[i].Join();
            //    }
            //    if (children[19].CountTacts == 41)
            //    {
            //        for (int i = 20; i < 40; i++)
            //        {
            //            threads[i] = new Thread(children[i - 20].ChildSend);
            //            threads[i].Start();
            //        }
            //        Console.WriteLine($"Кол-во блоков на локальных компьюетрах после начала отправки: {children[19].Blocks}");
            //        for (int i = 20; i < 40; i++)
            //        {
            //            threads[i - 20].Join();
            //        }
            //    }

            //    if (central.Blocks == 0 && children[19].CountTacts == 41)
            //        break;
            //    Thread.Sleep(100);
            //}
            //--------------Для нашей параллельной-----------
            for (; ; )
            {
                central.CentralDataProcessing();
                if (children[19].Blocks != 20)
                {
                    if (children[19].Blocks > 16)
                    {
                        central.CentralSend();
                        Console.WriteLine($"Кол-во блоков на локальных компьюетрах после отправки с главного: {children[19].Blocks}");
                        children[0].Blocks -= 1;
                        children[1].Blocks -= 1;
                    }

                }


                Thread[] threads = new Thread[40];
                for (int i = 0; i < 20; i++)
                {
                    threads[i] = new Thread(children[i].ChildDataProcessing);
                    threads[i].Start();
                }
                //Console.WriteLine($"Кол-во блоков на локальных компьюетрах: {children[19].Blocks}");
                for (int i = 0; i < 20; i++)
                {
                    threads[i].Join();
                }
                if (children[19].CountTacts == 41)
                {
                    for (int i = 22; i < 40; i++)
                    {
                        threads[i] = new Thread(children[i - 20].ChildSend);
                        threads[i].Start();
                    }
                    Console.WriteLine($"Кол-во блоков на локальных компьюетрах после начала отправки: {children[19].Blocks}");
                    for (int i = 22; i < 40; i++)
                    {
                        threads[i - 20].Join();
                    }
                }
                threads[20] = new Thread(children[0].ChildSend);
                threads[21] = new Thread(children[1].ChildSend);
                threads[20].Start();
                threads[21].Start();
                threads[20].Join();
                threads[21].Join();
                if (central.Blocks == 0 && children[19].CountTacts == 41)
                    break;
                Thread.Sleep(100);
            }

        }
    }

    
    class CentralObject
    {
        
        object obj = 3;
        public int Blocks = 0;
        ChildObject[] Children = new ChildObject[20];
        //public CentralObject()
        //{
        //    for (int i = 0; i < 20; i++)
        //    {
        //        Children[i] = new ChildObject();
        //    }
        //}
        public Barrier barrier = new Barrier(20);
       
        public void CentralSend()
        {
                for (int i = 0; i < 20; i++)
                {
                    Children[i].Blocks++;
                }
        }
       
        public void CentralDataProcessing()
        {
            if (Blocks != 0)
            { 
                lock (obj)
                {
                    Blocks--;
                    Thread.Sleep(500);
                }
                Console.WriteLine($"Центральный компьютер обработал 1 блок, осталось {Blocks}");
            }
        }
        
        public void Link(ChildObject[] A)
        {
            for (int i = 0; i < 20; i++)
            {
                Children[i] = A[i];
            }
        }
        public void Start()
        {
            
            
        }

    }
    class ChildObject
    {
        public int Blocks = 17;
        public int CountTacts = 0;
        public object obj = 3;
        public CentralObject Central;
        public bool start = false;
        //public ChildObject()
        //{

        //}
        public ChildObject(CentralObject A)
        {
            Central = A;
        }
        public void ChildSend()
        {
            if (Blocks > 0 && Blocks <= 17)
            {
                
                lock (obj)
                {
                    Blocks--;
                    Central.Blocks++;
                }
            }
            

        }
            //Thread.Sleep(500);
            //Console.WriteLine($" Локальные компьютеры отправил 1 блок, осталось {Blocks}");


        
        public void ChildDataProcessing()
        {
            
            
            if (Blocks == 20)
            {
                start = true;
            }
            if (start == true)
            {
                CountTacts++;
            }
            if (CountTacts == 40)
            {
                start = false;
                Blocks = 7;
                CountTacts++;
                
            }
           

        }
    }

}
 

