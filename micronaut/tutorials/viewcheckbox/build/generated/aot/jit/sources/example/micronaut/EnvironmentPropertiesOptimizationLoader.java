package example.micronaut;

import io.micronaut.core.optim.StaticOptimizations;
import io.micronaut.core.util.EnvironmentProperties;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvironmentPropertiesOptimizationLoader implements StaticOptimizations.Loader<EnvironmentProperties> {
  private void load0(Map<String, List<String>> env) {
    env.put("PATH", Arrays.asList("path"));
    env.put("MANPATH", Arrays.asList("manpath"));
    env.put("CONDA_DEFAULT_ENV", Arrays.asList("conda.default.env", "conda.default-env", "conda-default.env", "conda-default-env"));
    env.put("CONDA_EXE", Arrays.asList("conda.exe", "conda-exe"));
    env.put("CONDA_PYTHON_EXE", Arrays.asList("conda.python.exe", "conda.python-exe", "conda-python.exe", "conda-python-exe"));
    env.put("TERM", Arrays.asList("term"));
    env.put("FIG_JETBRAINS_SHELL_INTEGRATION", Arrays.asList("fig.jetbrains.shell.integration", "fig.jetbrains.shell-integration", "fig.jetbrains-shell.integration", "fig.jetbrains-shell-integration", "fig-jetbrains.shell.integration", "fig-jetbrains.shell-integration", "fig-jetbrains-shell.integration", "fig-jetbrains-shell-integration"));
    env.put("HOMEBREW_PREFIX", Arrays.asList("homebrew.prefix", "homebrew-prefix"));
    env.put("COMMAND_MODE", Arrays.asList("command.mode", "command-mode"));
    env.put("CONDA_PREFIX", Arrays.asList("conda.prefix", "conda-prefix"));
    env.put("_CE_M", Arrays.asList(".ce.m", ".ce-m", "-ce.m", "-ce-m"));
    env.put("LOGNAME", Arrays.asList("logname"));
    env.put("HOMEBREW_REPOSITORY", Arrays.asList("homebrew.repository", "homebrew-repository"));
    env.put("PWD", Arrays.asList("pwd"));
    env.put("XPC_SERVICE_NAME", Arrays.asList("xpc.service.name", "xpc.service-name", "xpc-service.name", "xpc-service-name"));
    env.put("CONDA_SHLVL", Arrays.asList("conda.shlvl", "conda-shlvl"));
    env.put("INFOPATH", Arrays.asList("infopath"));
    env.put("__CFBundleIdentifier", Arrays.asList("..cfbundleidentifier", ".-cfbundleidentifier", "-.cfbundleidentifier", "--cfbundleidentifier"));
    env.put("SHELL", Arrays.asList("shell"));
    env.put("HOMEBREW_CELLAR", Arrays.asList("homebrew.cellar", "homebrew-cellar"));
    env.put("USER", Arrays.asList("user"));
    env.put("TERMINAL_EMULATOR", Arrays.asList("terminal.emulator", "terminal-emulator"));
    env.put("TMPDIR", Arrays.asList("tmpdir"));
    env.put("SSH_AUTH_SOCK", Arrays.asList("ssh.auth.sock", "ssh.auth-sock", "ssh-auth.sock", "ssh-auth-sock"));
    env.put("_CE_CONDA", Arrays.asList(".ce.conda", ".ce-conda", "-ce.conda", "-ce-conda"));
    env.put("XPC_FLAGS", Arrays.asList("xpc.flags", "xpc-flags"));
    env.put("TERM_SESSION_ID", Arrays.asList("term.session.id", "term.session-id", "term-session.id", "term-session-id"));
    env.put("__CF_USER_TEXT_ENCODING", Arrays.asList("..cf.user.text.encoding", "..cf.user.text-encoding", "..cf.user-text.encoding", "..cf.user-text-encoding", "..cf-user.text.encoding", "..cf-user.text-encoding", "..cf-user-text.encoding", "..cf-user-text-encoding", ".-cf.user.text.encoding", ".-cf.user.text-encoding", ".-cf.user-text.encoding", ".-cf.user-text-encoding", ".-cf-user.text.encoding", ".-cf-user.text-encoding", ".-cf-user-text.encoding", ".-cf-user-text-encoding", "-.cf.user.text.encoding", "-.cf.user.text-encoding", "-.cf.user-text.encoding", "-.cf.user-text-encoding", "-.cf-user.text.encoding", "-.cf-user.text-encoding", "-.cf-user-text.encoding", "-.cf-user-text-encoding", "--cf.user.text.encoding", "--cf.user.text-encoding", "--cf.user-text.encoding", "--cf.user-text-encoding", "--cf-user.text.encoding", "--cf-user.text-encoding", "--cf-user-text.encoding", "--cf-user-text-encoding"));
    env.put("CONDA_PROMPT_MODIFIER", Arrays.asList("conda.prompt.modifier", "conda.prompt-modifier", "conda-prompt.modifier", "conda-prompt-modifier"));
    env.put("LC_CTYPE", Arrays.asList("lc.ctype", "lc-ctype"));
    env.put("HOME", Arrays.asList("home"));
    env.put("SHLVL", Arrays.asList("shlvl"));
  }

  @Override
  public EnvironmentProperties load() {
    Map<String, List<String>> env = new HashMap<String, List<String>>();
    load0(env);
    return EnvironmentProperties.of(env);
  }
}
