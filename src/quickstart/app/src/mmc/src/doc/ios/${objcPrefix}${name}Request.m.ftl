//
//  ${objcPrefix}${name}Request.m
//  ${description}.请求报文
//
//  Created by 代码生成器1.0.
//

#import "${objcPrefix}${name}Request.h"
#import "${objcPrefix}${name}Response.h"

<#list requestGroups as group>
	<#list group.fields as field>
#define C_${group.id?upper_case}_${field.id?upper_case}        @"${field.id}"
	</#list>

@interface ${objcPrefix}${name}Element${group.id?cap_first} ()

@end

@implementation ${objcPrefix}${name}Element${group.id?cap_first}

-(id)init {
    self = [super init];
    if ( self ) {
    }
    return self;
}

	<#list group.fields as field>
-(void)set${field.id?cap_first}:(${field.objcType})value {
	id objcValue;
		<#if field.objcType == "int">
	objcValue = [NSNumber numberWithInt: value];
		<#elseif field.objcType == "double">
	objcValue = [NSNumber numberWithDouble: value];
		<#else>
	objcValue = value;
		</#if>
	self.context[C_${group.id?upper_case}_${field.id?upper_case}] = objcValue;
}

-(${field.objcType}) ${field.id} {
	
		<#if field.objcType == "int">
	NSNumber *value = self.context[C_${group.id?upper_case}_${field.id?upper_case}];
	return [value intValue];
		<#elseif field.objcType == "double">
	NSNumber *value = self.context[C_${group.id?upper_case}_${field.id?upper_case}];
	return [value doubleValue];
		<#else>
	${field.objcType} value = self.context[C_${group.id?upper_case}_${field.id?upper_case}];
	return value;
		</#if>
}
	</#list>

@end
</#list>

<#list requestFields as field>
#define C_${field.id?upper_case}      @"${field.id}"
</#list>
<#list requestGroups as group>
#define C_${group.id?upper_case}      @"${group.id}"
</#list>


@implementation ${objcPrefix}${name}Request

+(Class)responseClass {    
    return [${objcPrefix}${name}Response class];
}

+(NSString*)RA_type {
    return @"${id}";
}

-(id)init {
    self = [super init];
    if ( self ) {
    }
    return self;
}

<#list requestFields as field>
-(void)set${field.id?cap_first}:(${field.objcType})value {
	id objcValue;
		<#if field.objcType == "int">
	objcValue = [NSNumber numberWithInt: value];
		<#elseif field.objcType == "double">
	objcValue = [NSNumber numberWithDouble: value];
		<#else>
	objcValue = value;
		</#if>
	self.context[C_${field.id?upper_case}] = objcValue;
}

-(${field.objcType})${field.id} {
		<#if field.objcType == "int">
	NSNumber *value = self.context[C_${field.id?upper_case}];
	return [value intValue];
		<#elseif field.objcType == "double">
	NSNumber *value = self.context[C_${field.id?upper_case}];
	return [value doubleValue];
		<#else>
	${field.objcType} value = self.context[C_${field.id?upper_case}];
	return value;
		</#if>
}
</#list>

<#list requestGroups as group>
-(void)set${group.id?cap_first}:(NSArray*)subList {
	NSMutableArray *newArray = [NSMutableArray new];
	
 	for (${objcPrefix}${name}Element${group.id?cap_first} *sub in subList) {
		[newArray addObject:sub.context];
	}
	self.context[C_${group.id?upper_case}] = newArray;
}

-(NSArray*)${group.id} {
	NSMutableArray *groupList = [NSMutableArray new];
	NSArray *subList = self.context[C_${group.id?upper_case}];
	
	for (NSMutableDictionary *subContext in subList) {
		${objcPrefix}${name}Element${group.id?cap_first} *newSubValue = [${objcPrefix}${name}Element${group.id?cap_first} new];
		newSubValue.context = subContext;
		[groupList addObject:newSubValue];
	}
	
    return groupList;
}

</#list>

@end


