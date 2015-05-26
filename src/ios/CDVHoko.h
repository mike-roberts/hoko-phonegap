#import <Cordova/CDV.h>

@interface CDVHoko : CDVPlugin

- (void)setup:(CDVInvokedUrlCommand*)command;
- (void)mapRoute:(CDVInvokedUrlCommand*)command;
- (void)addHandler:(CDVInvokedUrlCommand*)command;

@end
