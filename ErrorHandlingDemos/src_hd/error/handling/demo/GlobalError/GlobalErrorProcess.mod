[Ivy]
[>Created: Mon Sep 28 17:45:30 CEST 2015]
15014A15C1CA5451 3.18 #module
>Proto >Proto Collection #zClass
Gs0 GlobalErrorProcess Big #zClass
Gs0 RD #cInfo
Gs0 #process
Gs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Gs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Gs0 @TextInP .resExport .resExport #zField
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @MessageFlowInP-0n messageIn messageIn #zField
Gs0 @MessageFlowOutP-0n messageOut messageOut #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @RichDialogInitStart f0 '' #zField
Gs0 @RichDialogProcessEnd f1 '' #zField
Gs0 @PushWFArc f2 '' #zField
Gs0 @RichDialogProcessStart f3 '' #zField
Gs0 @RichDialogEnd f4 '' #zField
Gs0 @PushWFArc f5 '' #zField
>Proto Gs0 Gs0 GlobalErrorProcess #zField
Gs0 f0 guid 15014A15C2CFD098 #txt
Gs0 f0 type error.handling.demo.GlobalError.GlobalErrorData #txt
Gs0 f0 method start(String,String) #txt
Gs0 f0 disableUIEvents true #txt
Gs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String errorCode,java.lang.String errorText> param = methodEvent.getInputArguments();
' #txt
Gs0 f0 inParameterMapAction 'out.errorCode=param.errorCode;
out.errorText=param.errorText;
' #txt
Gs0 f0 outParameterDecl '<> result;
' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,String)</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 -51 15 #rect
Gs0 f0 @|RichDialogInitStartIcon #fIcon
Gs0 f1 type error.handling.demo.GlobalError.GlobalErrorData #txt
Gs0 f1 211 51 26 26 0 12 #rect
Gs0 f1 @|RichDialogProcessEndIcon #fIcon
Gs0 f2 expr out #txt
Gs0 f2 109 64 211 64 #arcP
Gs0 f3 guid 15014A15C38C0427 #txt
Gs0 f3 type error.handling.demo.GlobalError.GlobalErrorData #txt
Gs0 f3 actionDecl 'error.handling.demo.GlobalError.GlobalErrorData out;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Gs0 f3 83 147 26 26 -14 12 #rect
Gs0 f3 @|RichDialogProcessStartIcon #fIcon
Gs0 f4 type error.handling.demo.GlobalError.GlobalErrorData #txt
Gs0 f4 guid 15014A15C39ED220 #txt
Gs0 f4 211 147 26 26 0 12 #rect
Gs0 f4 @|RichDialogEndIcon #fIcon
Gs0 f5 expr out #txt
Gs0 f5 109 160 211 160 #arcP
>Proto Gs0 .type error.handling.demo.GlobalError.GlobalErrorData #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
>Proto Gs0 '' #fIcon
Gs0 f0 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect
Gs0 f3 mainOut f5 tail #connect
Gs0 f5 head f4 mainIn #connect
