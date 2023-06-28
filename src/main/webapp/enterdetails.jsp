<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head><link rel="stylesheet" type="text/css" href="mystyle.css"><script type="text/javascript" src="details.js"></script><title>Entry</title></head>

	<body bgcolor="lightblue"  ><nav>
			<img class="logo" src="t9.png" align="left" size="20px">

			<a href="index.jsp" class="navbar" >TIMETABLE</a>
			
                    		
                </nav><section class="one">
	<center><h1> Enter the details and submit to generate the timetable you want!!! </h1><br>
            <pre><center><form onsubmit="return checkDetails(this);" method="get" action="EnterDetails"><fieldset>
Number of Days in a week		<input required type='number' name='days'max="7" min="1" size="30" placeholder="integer">

Number of classes			<input required  min="1" type='number' size="30" name='class'  placeholder="integer">

Number of lectures in a day 		<input required min="1" type="number" name="lectures" placeholder="integer">
 
Number of lectures per subject in a week<input required  min="1" type="number" size="30" name="lecturespersub" placeholder="integer">

Starting Time 				<input required  min="1" type="number" min="0" max="2359" name="starttime" placeholder="(in hours)" >

Duration of each lecture 		<input required  min="1" type="number"  size="30" name="duration" placeholder="duration (in hours)">

Number of teachers 			<input required  min="1" type="number"  size="30" name="teachers" placeholder="integer">

Number of Subjects per class 		<input required  min="1" type="number"  size="30" name="subjects" placeholder="integer">
				
				<br><br>
                <input class="button" type='submit' value='SUBMIT'>         <input class="button" type="reset" value="Reset!"><br>
</center></pre></fieldset>
        </form></section>
</body>
</html>