#import "CDVHoko.h"

@implementation CDVHoko

- (void)setup:(CDVInvokedUrlCommand*)command
{
    NSString *callbackId = [command callbackId];
    NSString *token = [[command arguments] objectAtIndex:0];

    [Hoko setupWithToken:token debugMode:YES];

    CDVPluginResult* result = [CDVPluginResult
                               resultWithStatus:CDVCommandStatus_OK];

    [self success:result callbackId:callbackId];
}

- (void)mapRoute:(CDVInvokedUrlCommand*)command
{
    NSString *callbackId = [command callbackId];
    NSString *route = [[command arguments] objectAtIndex:0];

    [[Hoko deeplinking] mapRoute:route toTarget:^(HKDeeplink *deeplink){
      CDVPluginResult* result = [CDVPluginResult
                               resultWithStatus:CDVCommandStatus_OK
                               messageAsDictionary:deeplink.json];
      [self success:result callbackId:callbackId];
    }];
}

- (void)addHandler:(CDVInvokedUrlCommand*)command
{
    NSString *callbackId = [command callbackId];

    [[Hoko deeplinking] addHandlerBlock:^(HKDeeplink *deeplink){
      CDVPluginResult* result = [CDVPluginResult
                               resultWithStatus:CDVCommandStatus_OK
                               messageAsDictionary:deeplink.json];
      [self success:result callbackId:callbackId];
    }];
}

@end
