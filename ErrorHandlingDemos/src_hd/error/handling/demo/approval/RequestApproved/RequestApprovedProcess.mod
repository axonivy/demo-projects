[Ivy]
1508513598141BB4 3.28 #module
>Proto >Proto Collection #zClass
Rs0 RequestApprovedProcess Big #zClass
Rs0 RD #cInfo
Rs0 #process
Rs0 @TextInP .type .type #zField
Rs0 @TextInP .processKind .processKind #zField
Rs0 @AnnotationInP-0n ai ai #zField
Rs0 @MessageFlowInP-0n messageIn messageIn #zField
Rs0 @MessageFlowOutP-0n messageOut messageOut #zField
Rs0 @TextInP .xml .xml #zField
Rs0 @TextInP .responsibility .responsibility #zField
Rs0 @UdInit f0 '' #zField
Rs0 @UdProcessEnd f1 '' #zField
Rs0 @PushWFArc f2 '' #zField
Rs0 @UdEvent f3 '' #zField
Rs0 @UdExitEnd f4 '' #zField
Rs0 @PushWFArc f5 '' #zField
>Proto Rs0 Rs0 RequestApprovedProcess #zField
Rs0 f0 guid 150851359A0687B3 #txt
Rs0 f0 method start(error.handling.demo.Approval) #txt
Rs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<error.handling.demo.Approval approval> param = methodEvent.getInputArguments();
' #txt
Rs0 f0 inParameterMapAction 'out.approval=param.approval;
' #txt
Rs0 f0 outParameterDecl '<> result;
' #txt
Rs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Approval)</name>
    </language>
</elementInfo>
' #txt
Rs0 f0 83 51 26 26 -39 15 #rect
Rs0 f0 @|UdInitIcon #fIcon
Rs0 f1 211 51 26 26 0 12 #rect
Rs0 f1 @|UdProcessEndIcon #fIcon
Rs0 f2 expr out #txt
Rs0 f2 109 64 211 64 #arcP
Rs0 f3 guid 150851359C04938B #txt
Rs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Rs0 f3 83 147 26 26 -15 12 #rect
Rs0 f3 @|UdEventIcon #fIcon
Rs0 f4 211 147 26 26 0 12 #rect
Rs0 f4 @|UdExitEndIcon #fIcon
Rs0 f5 expr out #txt
Rs0 f5 109 160 211 160 #arcP
>Proto Rs0 .type error.handling.demo.approval.RequestApproved.RequestApprovedData #txt
>Proto Rs0 .processKind HTML_DIALOG #txt
>Proto Rs0 -8 -8 16 16 16 26 #rect
>Proto Rs0 '' #fIcon
Rs0 f0 mainOut f2 tail #connect
Rs0 f2 head f1 mainIn #connect
Rs0 f3 mainOut f5 tail #connect
Rs0 f5 head f4 mainIn #connect
