 public boolean hitOtherBallVector()
    {
        boolean result=false;
        ArrayList<Ball> current=null;
        for(int k=-1;k<2;k++)
        {
            switch(k)
            {
                case -1:current=null;break;
                case 0:current=myGameData.getPlayer1_balls();break;
                case 1:current=myGameData.getPlayer2_balls();break;
            }
            int max;
            Ball temp;
            if (current==null)
                max=1;
            else
                max=current.size();

            for(int i=0;i<max;i++)
            {
                if (k==-1)
                    temp=myGameData.getFootball();
                else
                    temp = current.get(i);
                //If not same ball
                if (temp!=this)
                {
                    //If hit
                    if (distance(temp)<=(this.radius/2)+(temp.radius/2))
                    {
                        result=true;

                        float pom_x=this.mFigurePosition.x-temp.mFigurePosition.x;
                        float pom_y=this.mFigurePosition.y-temp.mFigurePosition.y;

                        float resultant_y;
                        float resultant_x;
                        if (pom_y==0)
                        {
                            //Log.d("MY_LOG","deljenje nulom ispravljen problem");
                            float jacina=(float)Math.sqrt((vectorX)*(vectorX)+(vectorY)*(vectorY));

                            resultant_y=0;
                            resultant_x=jacina;

                        }else
                        {
                            float odnos=Math.abs(pom_x/pom_y);
                            float jacina=(float)Math.sqrt((vectorX)*(vectorX)+(vectorY)*(vectorY));

                            resultant_y=jacina/(float)Math.sqrt(odnos*odnos+1);
                            resultant_x=resultant_y*odnos;

                        }
                        if (pom_x<0)
                        {
                            resultant_x=(-resultant_x);
                        }
                        if (pom_y<0)
                        {
                            resultant_y=(-resultant_y);
                        }

                        //this.mFigurePosition.x-=this.vectorX;
                        //this.mFigurePosition.y-=this.vectorY;
                        this.vectorX+=StaticValues.myVectorDecipation*resultant_x;
                        this.vectorY+=StaticValues.myVectorDecipation*resultant_y;
                        //this.setMoving(true);
                        temp.vectorX-=StaticValues.otherVectorDecipation*resultant_x;
                        temp.vectorY-=StaticValues.otherVectorDecipation*resultant_y;
                        //temp.setMoving(true);
                    }
                }
            }

        }
        return result;
    }