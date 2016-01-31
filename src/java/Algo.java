public class Algo 
{
    int i,j,k,time,duration,startingTime;
    int days,noClasses,noSubjects,lecturesInDay,noTeachers;
    int noLecturesPerSubject; //noe considered constant for each subject
    int noLectures[];   //number of lectures per subject; now hardcoded
    String teacher[];
    int availability[][][];
    String subs[][][];
    String table[][][];
        
    public Algo(int day,int classes,int lectures,int lecturesPerSub,int time
            ,int dur,int noSub,int noTeach,String teachers,String subjects)
    {
        this.days=day;
        this.noClasses=classes;
        this.lecturesInDay=lectures;
        this.noLecturesPerSubject=lecturesPerSub;
        this.startingTime=time;
        this.duration=dur;
        this.noSubjects=noSub;
        this.noTeachers=noTeach;
        
        this.noLectures=new int[noSubjects];
        for(i=0;i<noSub;i++)
            noLectures[i]=noLecturesPerSubject;
        
        //Check this
        teacher=teachers.split(",");
        
        int teacherID,newTeacherID;   //to point to first index of availability or teacher[]
        
        availability=new int[noTeachers][days][lecturesInDay];
        //0 means teacher is available 1 means busy
        // hardcode all values for now
        
        for(i=0;i<noTeachers;i++)
            for(j=0;j<days;j++)
                for(k=0;k<lecturesInDay;k++)
                    availability[i][j][k]=0; 
        
        //OS.A,CN.B,TOC.C,SE.D,:DS.A,DIS.B,MA.C,DE.D,:Chem.A,phy.B,maths.C,bio.D,:
        subs=new String[noClasses][2][noSubjects];
        String tempstr[]=new String[noClasses];
        tempstr=subjects.split(":");
        int p,q;
        String tempstr2[]=new String[noSubjects];
        
        for(i=0;i<noClasses;i++)
        {
            p=0;q=0;
            tempstr2=tempstr[i].split(",");
            for(j=0;j<tempstr2.length;j++)
                System.out.println(tempstr2[j]);
        
            for(k=0;k<noSubjects;k++)
            {
                q=tempstr2[k].indexOf('.',p);
                subs[i][0][k]=tempstr2[k].substring(p,q);
                subs[i][1][k]=tempstr2[k].substring(q+1,tempstr2[k].length());
            }
        }

        //----------------algo-------------------------
        table=new String[noClasses][days][lecturesInDay];
        int remainingLectures[][]=new int[noClasses][noSubjects];
        //index 1 will hold remaining lectures of (index2) subject 0=subs[0];and so on
        
        for(j=0;j<noClasses;j++)
        {
            for(i=0;i<noSubjects;i++)
            {
                remainingLectures[j][i]=noLectures[i]; //index of subject in subs[][] is same index here
            }
        } 
        
        int flag,f,i2,j2,k2;
        int free[] =new int[noClasses];
        for(i=0;i<noClasses;i++)
            free[i]=(days*lecturesInDay)-(noSubjects*noLecturesPerSubject);
        int sum=0;
        i=0;j=0;
        while(sum!=(noClasses*days*lecturesInDay))
        {
            teacherID=0;
            k=0;
            while(k<lecturesInDay)
            {
                for(f=0,flag=0;f<noSubjects;f++)
                    flag=flag+remainingLectures[i][f];
                if(flag==0 && free[i]>0)
                {
                    for(j2=0;j2<days;j2++)
                        for(k2=0;k2<lecturesInDay;k2++)
                            if(table[i][j2][k2]==null && free[i]>0)
                            {
                                table[i][j2][k2]="free";
                                free[i]--;
                                sum++;
                                k++;
                                if(k==lecturesInDay)
                                    k=0;
                            }
                }
                if(table[i][j][k]==null)
                {
                    if(remainingLectures[i][teacherID]>0)
                    {
                        if(availability[teacherID][j][k]==0)
                        {
                            table[i][j][k]=subs[i][0][teacherID];
                            remainingLectures[i][teacherID]--;
                            availability[teacherID][j][k]=1;
                            if(teacherID==(noTeachers-1))
                                teacherID=0;
                            else    
                                teacherID++;
                            sum++;
                            k++;
                            if(k==lecturesInDay)
                                    k=0;
                        }
                        else
                        {	
                            newTeacherID=-1;
                            int s;
                            for(s=0;s<noTeachers;s++)
                                if(availability[s][j][k]==0 && remainingLectures[i][s]>0)
                                {
                                    newTeacherID=s;
                                    s=noTeachers;
                                }
                            if(newTeacherID==-1)
                            {
                                if(free[i]==0)
                                {
                                    i2=0;
                                    int temp=0; 
                                    for(s=0;s<noTeachers;s++)
                                    {
                                        if(availability[s][j][k]==0)
                                        {    
                                            newTeacherID=s;
                                            for(j2=0;j2<days;j2++)
                                            {
                                                for(k2=0;k2<lecturesInDay;k2++)
                                                {

                                                    if(table[i][j2][k2]==subs[i][0][newTeacherID] && availability[teacherID][j2][k2]==0)
                                                    {
                                                        table[i][j2][k2]=subs[i][0][teacherID];
                                                        remainingLectures[i][teacherID]--;
                                                        availability[teacherID][j2][k2]=1;

                                                        table[i][j][k]=subs[i][0][newTeacherID];
                                                        temp++;

                                                        if(teacherID==(noTeachers-1))
                                                            teacherID=0;
                                                        else    
                                                            teacherID++;
                                                        sum++;
                                                        k++;
                                                        if(k==lecturesInDay)
                                                            k=0;
                                                        break;
                                                    }
                                                }
                                                if(temp>0)
                                                break;
                                            }
                                            if(temp>0)
                                                break;
                                        }
                                    }
                                }
                                else
                                {
                                    table[i][j][k]="free";
                                    free[i]--;
                                    sum++;
                                    k++;
                                }    
                                if(k==lecturesInDay)
                                    k=0;
                            }
                            else
                                teacherID=newTeacherID;
                        }
                    }
                    else
                    {
                        int temp=0;
                        int s;
                        for(s=1;s<noSubjects;s++)
                            if(remainingLectures[i][temp]<remainingLectures[i][s])
                                temp=s;
                        teacherID=temp;
                    }
                }
                else
                {
                    k++;
                }

            }
            j++;
            if(j==days)
            {
                j=0;
                i++;
                if(i==noClasses)
                    i=0;
            }
        }
    }    
}