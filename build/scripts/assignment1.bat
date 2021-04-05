@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  assignment1 startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and ASSIGNMENT1_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\assignment1-3.0.1.jar;%APP_HOME%\lib\metrics-2.0.25.jar;%APP_HOME%\lib\server-2.0.25.jar;%APP_HOME%\lib\info-2.0.25.jar;%APP_HOME%\lib\logger-config-2.0.25.jar;%APP_HOME%\lib\openapi-validator-2.0.25.jar;%APP_HOME%\lib\openapi-security-2.0.25.jar;%APP_HOME%\lib\specification-2.0.25.jar;%APP_HOME%\lib\security-2.0.25.jar;%APP_HOME%\lib\portal-registry-2.0.25.jar;%APP_HOME%\lib\consul-2.0.25.jar;%APP_HOME%\lib\client-2.0.25.jar;%APP_HOME%\lib\openapi-meta-2.0.25.jar;%APP_HOME%\lib\audit-2.0.25.jar;%APP_HOME%\lib\health-2.0.25.jar;%APP_HOME%\lib\dump-2.0.25.jar;%APP_HOME%\lib\sanitizer-2.0.25.jar;%APP_HOME%\lib\body-2.0.25.jar;%APP_HOME%\lib\traceability-2.0.25.jar;%APP_HOME%\lib\correlation-2.0.25.jar;%APP_HOME%\lib\exception-2.0.25.jar;%APP_HOME%\lib\handler-2.0.25.jar;%APP_HOME%\lib\cluster-2.0.25.jar;%APP_HOME%\lib\balance-2.0.25.jar;%APP_HOME%\lib\registry-2.0.25.jar;%APP_HOME%\lib\monad-result-2.0.25.jar;%APP_HOME%\lib\status-2.0.25.jar;%APP_HOME%\lib\mask-2.0.25.jar;%APP_HOME%\lib\common-2.0.25.jar;%APP_HOME%\lib\service-2.0.25.jar;%APP_HOME%\lib\switcher-2.0.25.jar;%APP_HOME%\lib\config-2.0.25.jar;%APP_HOME%\lib\http-entity-2.0.25.jar;%APP_HOME%\lib\http-string-2.0.25.jar;%APP_HOME%\lib\openapi-helper-2.0.25.jar;%APP_HOME%\lib\openapi-parser-2.0.25.jar;%APP_HOME%\lib\json-overlay-2.0.25.jar;%APP_HOME%\lib\http-url-2.0.25.jar;%APP_HOME%\lib\utility-2.0.25.jar;%APP_HOME%\lib\decryptor-2.0.25.jar;%APP_HOME%\lib\json-schema-validator-1.0.49.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.12.1.jar;%APP_HOME%\lib\jackson-databind-2.12.1.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\undertow-core-2.2.4.Final.jar;%APP_HOME%\lib\mongo-java-driver-3.12.8.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.12.1.jar;%APP_HOME%\lib\jose4j-0.6.3.jar;%APP_HOME%\lib\json-path-2.4.0.jar;%APP_HOME%\lib\jaeger-client-0.35.2.jar;%APP_HOME%\lib\jaeger-thrift-0.35.2.jar;%APP_HOME%\lib\jaeger-tracerresolver-0.35.2.jar;%APP_HOME%\lib\jaeger-core-0.35.2.jar;%APP_HOME%\lib\libthrift-0.12.0.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\encoder-1.2.1.jar;%APP_HOME%\lib\snakeyaml-1.27.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\caffeine-2.6.2.jar;%APP_HOME%\lib\HdrHistogram-2.1.10.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\jackson-core-2.12.1.jar;%APP_HOME%\lib\jackson-annotations-2.12.1.jar;%APP_HOME%\lib\javax.mail-1.6.1.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\commons-lang3-3.7.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\xnio-nio-3.8.0.Final.jar;%APP_HOME%\lib\xnio-api-3.8.0.Final.jar;%APP_HOME%\lib\wildfly-client-config-1.0.1.Final.jar;%APP_HOME%\lib\jboss-threads-3.1.0.Final.jar;%APP_HOME%\lib\jboss-logging-3.4.1.Final.jar;%APP_HOME%\lib\opentracing-util-0.32.0.jar;%APP_HOME%\lib\opentracing-tracerresolver-0.1.6.jar;%APP_HOME%\lib\opentracing-noop-0.32.0.jar;%APP_HOME%\lib\opentracing-api-0.32.0.jar;%APP_HOME%\lib\activation-1.1.jar;%APP_HOME%\lib\wildfly-common-1.5.2.Final.jar;%APP_HOME%\lib\okhttp-3.9.0.jar;%APP_HOME%\lib\gson-2.8.2.jar;%APP_HOME%\lib\okio-1.13.0.jar


@rem Execute assignment1
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %ASSIGNMENT1_OPTS%  -classpath "%CLASSPATH%"  %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable ASSIGNMENT1_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%ASSIGNMENT1_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
