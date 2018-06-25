                                                                      # Smoker-Diary
                             Smoker-Diary is an Android app used to help smokers track their smoking habit on a daily basis.
                                                                      #ExistingCode:
                             Run all the basic functionality for the app(Track cig based on number of smoked cig and timestamp.)
                                                                                                                         
                                                                      #ToDo: 
                                                    UI design -> Days division in List view.
                                                                 Clear main activity data in a new day.
																                                                     
                                                        i)ArrayList -> CardView design, divided by days/date
                                                        ii)JobService modification & test

                                        /\ create an item for each day based on the MainActivity.days /\
                                        /\ the day's number will be added to local SQlite under COL4 /\
                                        /\ onClickListener for each day in the list will open the numOfCig /\
                                        /\ for that specific day (depending on COL4 value). /\
													
										/\ create function that runs daily to create new listView item. /\
										/\ the new listView item will be named based on the day that /\
										/\ it was created on. The new item will have a value to determine /\
										/\ the new position in the listView for the onClickListener. /\
													
													
													
                                                                      **To Be Updated **
