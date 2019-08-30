[Ivy]
12E2EFDD83CB2D4C 3.28 #module
>Proto >Proto Collection #zClass
Ln0 Login Big #zClass
Ln0 B #cInfo
Ln0 #process
Ln0 @TextInP .type .type #zField
Ln0 @TextInP .processKind .processKind #zField
Ln0 @AnnotationInP-0n ai ai #zField
Ln0 @TextInP .xml .xml #zField
Ln0 @TextInP .responsibility .responsibility #zField
Ln0 @StartRequest f0 '' #zField
Ln0 @CallSub f5 '' #zField
Ln0 @EndRequest f2 '' #zField
Ln0 @PushWFArc f1 '' #zField
Ln0 @Alternative f4 '' #zField
Ln0 @PushWFArc f6 '' #zField
Ln0 @PushWFArc f3 '' #zField
Ln0 @EndTask f7 '' #zField
Ln0 @PushWFArc f8 '' #zField
>Proto Ln0 Ln0 Login #zField
Ln0 f0 outLink DefaultLoginPage.ivp #txt
Ln0 f0 inParamDecl '<java.lang.String originalUrl> param;' #txt
Ln0 f0 inParamTable 'out.temp.url=param.originalUrl;
' #txt
Ln0 f0 requestEnabled true #txt
Ln0 f0 triggerEnabled false #txt
Ln0 f0 callSignature DefaultLoginPage(String) #txt
Ln0 f0 persist false #txt
Ln0 f0 startName 'WF Login' #txt
Ln0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ln0 f0 showInStartList 0 #txt
Ln0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultLoginPage.ivp</name>
        <nameStyle>20,5,7
</nameStyle>
        <desc>This start is used to overwrite the default login.
Use /ivy/wf/login to call it.
This process is called whenever a login is required.</desc>
    </language>
</elementInfo>
' #txt
Ln0 f0 @C|.responsibility Everybody #txt
Ln0 f0 115 43 26 26 18 -9 #rect
Ln0 f0 @|StartRequestIcon #fIcon
Ln0 f5 processCall 'Functional Processes/LoginSequence:check_Login(htmlwfui.Data)' #txt
Ln0 f5 requestActionDecl '<htmlwfui.Data in> param;
' #txt
Ln0 f5 requestMappingAction 'param.in=in;
' #txt
Ln0 f5 responseActionDecl 'htmlwfui.Data out;
' #txt
Ln0 f5 responseMappingAction 'out=result.out;
' #txt
Ln0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check Login</name>
        <nameStyle>11,9
</nameStyle>
    </language>
</elementInfo>
' #txt
Ln0 f5 110 100 36 24 21 -6 #rect
Ln0 f5 @|CallSubIcon #fIcon
Ln0 f2 template "redirect.jsp" #txt
Ln0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect</name>
        <nameStyle>8
</nameStyle>
    </language>
</elementInfo>
' #txt
Ln0 f2 59 219 26 26 17 -5 #rect
Ln0 f2 @|EndRequestIcon #fIcon
Ln0 f1 expr out #txt
Ln0 f1 128 69 128 100 #arcP
Ln0 f1 0 0.48896339077831585 0 0 #arcLabel
Ln0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startUrl given?</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Ln0 f4 114 154 28 28 14 0 #rect
Ln0 f4 @|AlternativeIcon #fIcon
Ln0 f6 expr out #txt
Ln0 f6 128 124 128 154 #arcP
Ln0 f3 expr in #txt
Ln0 f3 outCond in.temp.url.length()>0 #txt
Ln0 f3 121 175 80 222 #arcP
Ln0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>default End</name>
        <nameStyle>11
</nameStyle>
    </language>
</elementInfo>
' #txt
Ln0 f7 187 219 26 26 14 0 #rect
Ln0 f7 @|EndIcon #fIcon
Ln0 f8 expr in #txt
Ln0 f8 135 175 190 223 #arcP
>Proto Ln0 .type htmlwfui.Data #txt
>Proto Ln0 .processKind NORMAL #txt
>Proto Ln0 0 0 32 24 18 0 #rect
>Proto Ln0 @|BIcon #fIcon
Ln0 f0 mainOut f1 tail #connect
Ln0 f1 head f5 mainIn #connect
Ln0 f5 mainOut f6 tail #connect
Ln0 f6 head f4 in #connect
Ln0 f4 out f3 tail #connect
Ln0 f3 head f2 mainIn #connect
Ln0 f4 out f8 tail #connect
Ln0 f8 head f7 mainIn #connect
