/**
 * Copyright 2015-2016 Maven Source Dependencies
 * Plugin contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.l2x6.srcdeps.core.config;

import java.lang.ProcessBuilder.Redirect;

import org.l2x6.srcdeps.core.shell.IoRedirects;

/**
 * A triple of definitions how to handle the three standard I/O steams of a process. The syntax of the definitions
 * mostly follows the semantics of {@link java.lang.ProcessBuilder.Redirect}.
 *
 * @see IoRedirects
 * @see IoRedirects#parseUri(String)
 * @author <a href="https://github.com/ppalaga">Peter Palaga</a>
 */
public class BuilderIo {

    public static class Builder {

        private String stderr = BuilderIoScheme.inherit.name();

        private String stdin = BuilderIoScheme.inherit.name();

        private String stdout = BuilderIoScheme.inherit.name();

        public BuilderIo build() {
            return new BuilderIo(stdin, stdout, stderr);
        }

        public Builder stderr(String stderr) {
            this.stderr = stderr;
            return this;
        }

        public Builder stdin(String stdin) {
            this.stdin = stdin;
            return this;
        }

        public Builder stdout(String stdout) {
            this.stdout = stdout;
            return this;
        }
    }

    /**
     * An enum of prefixes to use when encoding {@link Redirect}s as URI {@link String}s. See
     * {@link IoRedirects#parseUri(String)}.
     *
     */
    enum BuilderIoScheme {
        append, err2out, inherit, read, write
    }

    public static final BuilderIo INHERIT_ALL = new BuilderIo(BuilderIoScheme.inherit.name(),
            BuilderIoScheme.inherit.name(), BuilderIoScheme.inherit.name());

    public static final Builder builder() {
        return new Builder();
    }

    public static final BuilderIo inheritAll() {
        return INHERIT_ALL;
    }

    private final String stderr;

    private final String stdin;

    private final String stdout;

    private BuilderIo(String in, String out, String err) {
        super();
        this.stdin = in;
        this.stdout = out;
        this.stderr = err;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BuilderIo other = (BuilderIo) obj;
        if (stderr == null) {
            if (other.stderr != null)
                return false;
        } else if (!stderr.equals(other.stderr))
            return false;
        if (stdin == null) {
            if (other.stdin != null)
                return false;
        } else if (!stdin.equals(other.stdin))
            return false;
        if (stdout == null) {
            if (other.stdout != null)
                return false;
        } else if (!stdout.equals(other.stdout))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.l2x6.srcdeps.core.config.BuilderIo#getStderr()
     */
    public String getStderr() {
        return stderr;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.l2x6.srcdeps.core.config.BuilderIo#getStdin()
     */
    public String getStdin() {
        return stdin;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.l2x6.srcdeps.core.config.BuilderIo#getStdout()
     */
    public String getStdout() {
        return stdout;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stderr == null) ? 0 : stderr.hashCode());
        result = prime * result + ((stdin == null) ? 0 : stdin.hashCode());
        result = prime * result + ((stdout == null) ? 0 : stdout.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BuilderIo [stdin=" + stdin + ", stdout=" + stdout + ", stderr=" + stderr + "]";
    }

}
