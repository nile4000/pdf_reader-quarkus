/*
 * Copyright Gunnar Morling.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package dev.morling.quarkus.pdfextract;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import com.oracle.svm.core.annotate.AutomaticFeature;
import com.oracle.svm.hosted.jni.JNIRuntimeAccess;

import sun.java2d.DefaultDisposerRecord;

@AutomaticFeature
class JNIRegistrationFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        // TODO Auto-generated method stub
        return false;
    }

    // @Override
    // public void beforeAnalysis(BeforeAnalysisAccess access) {
    //     try {
    //         JNIRuntimeAccess.register(
    //                 DefaultDisposerRecord.class.getDeclaredMethod("invokeNativeDispose", long.class, long.class));
    //     } catch (NoSuchMethodException e) {
    //         throw new RuntimeException(e);
    //     }
    // }
}
