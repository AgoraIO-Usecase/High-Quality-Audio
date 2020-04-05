@echo off

title qmake and nmake build prompt
set SDKVersion=%~1
set SDKFolderVersion=%~2
set QTDIR=%~3
set ProjName=%~4
set Machine=%~5
echo SDKVersion: %SDKVersion%
echo SDKFolderVersion: %SDKFolderVersion%
echo ProjName:%ProjName%
curl -fsSL -o AgoraSDK.zip https://download.agora.io/sdk/release/Agora_Native_SDK_for_Windows(%Machine%)_v%SDKVersion%_FULL.zip
if exist AgoraSDK.zip (
  7z x AgoraSDK.zip -oAgoraSDK
) else (
  echo "download sdk failed"
  echo "https://download.agora.io/sdk/release/Agora_Native_SDK_for_Windows(%Machine%)_v%SDKVersion%_FULL.zip"
  exit
)

xcopy /S /I AgoraSDK\Agora_Native_SDK_for_Windows_v%SDKFolderVersion%_FULL\sdk sdk /y

if exist AgoraSDK (rmdir /S /Q AgoraSDK)
del AgoraSDK.zip

set VCINSTALLDIR=C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\VC\Auxiliary\Build

call "%VCINSTALLDIR%\vcvarsall.bat" %Machine%
%QTDIR%\bin\qmake.exe %ProjName%.pro "CONFIG+=release" "CONFIG+=qml_release"

nmake

if not exist release (
  echo "no release"
  exit
)

cd release
del *.h
del *.cpp
del *.obj

%QTDIR%\bin\windeployqt %ProjName%.exe
cd ..

echo "copy agora sdk"
copy sdk\dll\*.dll release /y

set PackageDIR=%ProjName%_Win_v%SDKFolderVersion%
if not exist %PackageDIR% (
  mkdir %PackageDIR%
)

7z a -tzip %PackageDIR%.zip  Release
rmdir /S /Q sdk
rmdir /S /Q Release
rmdir /S /Q %PackageDIR%

